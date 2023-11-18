package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
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
        } else {
            size = Files.readAllLines(PATH).size();
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
        if (key == null) {
            throw new IllegalArgumentException();
        }
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
                if (key.equals(currentKey)) {
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
        if (value == null) {
            throw new IllegalArgumentException();
        }
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
                if (value.equals(currentValue)) {
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
        if (key == null) {
            throw new IllegalArgumentException();
        }
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
        if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        try (RandomAccessFile file = new RandomAccessFile(PATH.toFile(), "rw")) {
            String currentLine;
            String currentKey;
            String currentValue;
            String[] keyValPair;
            while ((currentLine = file.readLine()) != null) {
                keyValPair = currentLine.split(":");
                if (keyValPair.length != 2) {
                    throw new IllegalArgumentException();
                }

                currentKey = keyValPair[0];
                currentValue = keyValPair[1];
                if (key.equals(currentKey)) {
                    this.remove(currentKey);
                    this.put(key, value);
                    return currentValue;
                }
            }

            file.seek(file.length());
            file.writeBytes(key + ":" + value + "\n");
            size++;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String remove(Object key) {
        String oldValue = null;
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String currentLine;
            String currentKey;
            String[] keyValPair;
            StringBuilder fileContents = new StringBuilder();
            while (reader.ready()) {
                currentLine = reader.readLine();
                keyValPair = currentLine.split(":");
                if (keyValPair.length != 2) {
                    throw new IllegalArgumentException();
                }

                currentKey = keyValPair[0];
                if (!key.equals(currentKey)) {
                    fileContents.append(currentLine).append("\n");
                } else {
                    oldValue = keyValPair[1];
                    size--;
                }
            }
            Files.write(PATH, fileContents.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return oldValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        try {
            Files.write(PATH, "".getBytes());
            size = 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        throw new UnsupportedOperationException();
    }
}
