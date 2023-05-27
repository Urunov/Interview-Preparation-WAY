package com.davr.core;

/**
 * @author hamdamboy
 * @project FactoryMethod
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date 5/20/23
 */
public abstract class OperatingSystemCore {
    //
    private final String version;
    private final String architecture;
     public OperatingSystemCore(String version, String architecture){
        this.version = version;
        this.architecture = architecture;
    }
    public abstract boolean changeDir(String dir);
    public abstract boolean removeDir(String dir);
}
