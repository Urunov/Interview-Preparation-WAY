package com.davr.core;

/**
 * @author hamdamboy
 * @project FactoryMethod
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date 5/20/23
 */
public class FactoryMethodOS {

    private FactoryMethodOS(){}

    public static OperatingSystemCore getInstance(String type, String version, String architecture){
        switch (type){
            case "WINDOWS":
                return new WindowsOperatingSystem(version, architecture);
            case "LINUX":
                return new LinuxOperatingSystem(version, architecture);
            default:
                throw new IllegalArgumentException("OS not supported.");
        }
    }

    public static void main(String []args){
        OperatingSystemCore operatingSystem = FactoryMethodOS.getInstance("WINDOWS" , "WIN7" ,"x64");
        OperatingSystemCore operatingSystem2 = FactoryMethodOS.getInstance("LINUX" , "DEB" ,"x64");
      //OperatingSystemCore operatingMACOS = FactoryMethodOS.getInstance("MAC", "Monterey", "12.6.5");

      //    operatingMACOS.changeDir("cd");
        System.out.println(operatingSystem.changeDir("/"));
        System.out.println(operatingSystem.removeDir("delete"));

        System.out.println(operatingSystem2.removeDir("rm/"));
        System.out.println(operatingSystem2.changeDir("../"));

//        System.out.println(operatingSystem2);
//        System.out.println(operatingSystem);
    }
}
