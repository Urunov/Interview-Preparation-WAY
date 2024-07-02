package uz.project.service.caller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import uz.project.exception.condition.ConnectionException;
import uz.project.service.LogProtocolService;
import uz.project.util.HeadConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * Authors: Hamdamboy & Hudoberdi
 * Date: 01.07.2024
 * Time: 9:45
 * Project: service
 */
@Component
public class CallerService {
    private static final Logger LOG = LoggerFactory.getLogger(CallerService.class);
    private final WebClient webClient;
    private final HeadConfig headerConfig;
    private final LogProtocolService logProtocolService;
    @Value("${companyName.url.protocol}")
    private String urlAddress;


    public CallerService(WebClient webClient, HeadConfig headerConfig,
                         LogProtocolService logProtocolService) {
        this.webClient = webClient;
        this.headerConfig = headerConfig;
        this.logProtocolService = logProtocolService;
    }

    /**
     * Calls the company server and returns a GeneralResponseDTO.
     *
     * @param extraUrl the extra URL to be appended to the base URL
     * @return the GeneralResponseDTO response from the eGov server
     * @throws ConnectionException if there is an error during the request
     */
    public File callToEgovServer(String extraUrl, String date, String method) {
        LOG.info("REQUEST: call To Company Server By  Service : {}", extraUrl);
        final HttpServletRequest requestProject = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        try {
            byte[] responseBytes = webClient
                    .method(HttpMethod.GET)
                    .uri(urlAddress + extraUrl)
                    .headers(header -> header.addAll(headerConfig.getHeaders()))
                    .exchangeToMono(response -> {
                        final int statusCodeResponse = response.statusCode().value();
                        LOG.info("RESPONSE FROM SERVER STATUS : {}", statusCodeResponse);
                        logProtocolService.addLog(requestProject, "request.toString()", statusCodeResponse);

                        if (response.statusCode() != HttpStatus.OK) {
                            return Mono.error(new ConnectionException("Error Occur : " + response.statusCode() + "'"));
                        }
                        return response.bodyToMono(byte[].class);
                    })
                    .onErrorMap(error -> new ConnectionException(error.getMessage()))
                    .retryWhen(Retry.max(3))
                    .block();

            Path tempFilePath = Files.createTempFile(date + "_" + method, ".csv");
            Path renamedFilePath = tempFilePath.resolveSibling(date + "_" + method + ".csv");

            Files.write(tempFilePath, responseBytes);
            Files.move(tempFilePath, renamedFilePath, StandardCopyOption.REPLACE_EXISTING);

            return renamedFilePath.toFile();
        } catch (IOException e) {
            throw new ConnectionException("Error creating temporary file: " + e.getMessage());
        }
    }
}