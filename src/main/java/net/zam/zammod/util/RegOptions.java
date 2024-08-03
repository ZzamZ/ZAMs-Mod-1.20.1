package net.zam.zammod.util;

public class RegOptions<T> {
    public boolean isEnabled = true;

    public T enable(boolean b) {
        isEnabled = b;
        return (T)this;
    }
}