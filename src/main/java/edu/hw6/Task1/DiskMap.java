package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final Path PATH = Path.of("disk-map.txt");

    private int size;

    public DiskMap() throws IOException {
        if (!Files.exists(PATH)) {
            Files.createFile(PATH);
            size = 0;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean containsKey(Object key) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(PATH)) {
            String entry;
            String currentKey;
            String[] keyValPair;
            while (bufferedReader.ready()) {
                entry = bufferedReader.readLine();
                keyValPair = entry.split(":");
                if (keyValPair.length != 2) {
                    throw new IllegalArgumentException();
                }

                currentKey = keyValPair[0];
                if (key != null && key.equals(currentKey)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(PATH)) {
            String entry;
            String currentValue;
            String[] keyValPair;
            while (bufferedReader.ready()) {
                entry = bufferedReader.readLine();
                keyValPair = entry.split(":");
                if (keyValPair.length != 2) {
                    throw new IllegalArgumentException();
                }

                currentValue = keyValPair[1];
                if (value != null && value.equals(currentValue)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String get(Object key) {
        if (this.containsKey(key)) {
            try (BufferedReader bufferedReader = Files.newBufferedReader(PATH)) {
                String entry;
                String currentKey;
                String value;
                String[] keyValPair;
                while (bufferedReader.ready()) {
                    entry = bufferedReader.readLine();
                    keyValPair = entry.split(":");
                    if (keyValPair.length != 2) {
                        throw new IllegalArgumentException();
                    }

                    currentKey = keyValPair[0];
                    value = keyValPair[1];
                    if (key.equals(currentKey)) {
                        return value;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(PATH);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(PATH);
             RandomAccessFile randomAccessFile = new RandomAccessFile(PATH.toFile(), "rw")) {
            String entry;
            String currentKey;
            String[] keyValPair;
            while (bufferedReader.ready()) {
                entry = bufferedReader.readLine();
                keyValPair = entry.split(":");
                if (keyValPair.length != 2) {
                    throw new IllegalArgumentException();
                }

                currentKey = keyValPair[0];
                value = keyValPair[1];
                if (key.equals(currentKey)) {
                    return value;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return null;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }
}
