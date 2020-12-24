package ru.itis.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class Protocol {

    public static final byte START = 1;
    public static final byte MOVE = 2;
    public static final byte CHOOSE = 3;
    public static final byte NEXT_ROUND = 4;

    public static void write(byte command, Object object, OutputStream outputStreamRaw) {
        try {
            DataOutputStream outputStream = new DataOutputStream(outputStreamRaw);
            outputStream.writeByte(command);
            ObjectMapper mapper = new ObjectMapper();
            outputStream.writeUTF(mapper.writeValueAsString(object));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(byte command, OutputStream outputStreamRaw) {
        try {
            DataOutputStream outputStream = new DataOutputStream(outputStreamRaw);
            outputStream.writeByte(command);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> Object read(byte command, InputStream inputStreamRaw, OutputStream outputStreamRaw, Class<T> clas) {
        try {
            DataInputStream inputStream = new DataInputStream(inputStreamRaw);

            byte commandFromInput = inputStream.readByte();
            if (commandFromInput == command) {
                ObjectMapper mapper = new ObjectMapper();
                String msg = inputStream.readUTF();
                return mapper.readValue(msg, clas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean read(byte command, InputStream inputStreamRaw, OutputStream outputStreamRaw) {
        try {
            DataInputStream inputStream = new DataInputStream(inputStreamRaw);
            byte commandFromInput = inputStream.readByte();
            return commandFromInput == command;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
