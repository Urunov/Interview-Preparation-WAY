package com.davr.core;

/**
 * @author hamdamboy
 * @project FactoryMethod
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date 5/20/23
 */
public class WindowsOperatingSystem extends OperatingSystemCore{
    public WindowsOperatingSystem(String version, String architecture) {
        super(version, architecture);
    }
    @Override
    public boolean changeDir(String dir) {
        System.out.println("change dir: > " + dir);
        return true;
    }
    @Override
    public boolean removeDir(String dir) {
        System.out.println("remove dir: " + dir);
        return false;
    }
}
