package com.davr.core;

/**
 * @author hamdamboy
 * @project FactoryMethod
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date 5/20/23
 */
public class LinuxOperatingSystem extends OperatingSystemCore{
    public LinuxOperatingSystem(String version, String architecture) {
        super(version, architecture);
    }
    @Override
    public boolean changeDir(String dir) {
        System.out.println(" cd:/ > " + dir);
        return true;
    }

    @Override
    public boolean removeDir(String dir) {
        System.out.println("rm/: > " + dir);
        return true;
    }
}
