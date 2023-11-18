package edu.hw6.Task1;

import java.io.IOException;

public class TestDisk {
    public static void main(String[] args) throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("KEKW", "LULW");
        diskMap.put("CARL", "CARLA");
        diskMap.put("vector", "123");
        diskMap.put("vector1", "123");
        diskMap.put("vector2", "123");
        diskMap.put("vector3", "123");
        diskMap.put("vector4", "123");
        diskMap.put("vector5", "123");
        diskMap.put("vector6", "123");
        diskMap.put("vector7", "123");
        diskMap.put("vector8", "123");
        diskMap.put("vector9", "123");
        diskMap.put("KEKW", "1");
        diskMap.put("KEKW", "EKKWKEKOWWF");
        diskMap.put("KEKW", "EKKWKEKOWWFEKKWKEKOWWFEKKWKEKOWWFEKKWKEKOWWFEKKWKEKOWWF");
        diskMap.put("KEKW", "3");
        diskMap.clear();
        System.out.println(diskMap.size());
    }
}
