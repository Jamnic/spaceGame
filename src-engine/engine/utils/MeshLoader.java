package engine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import model.ship.Mesh;

import com.jogamp.common.nio.Buffers;

// TODO refactor javadoc
/**
 * Loads an .obj model from given file.
 */
public final class MeshLoader {

    /* ========== PUBLIC ========== */
    public static Mesh loadFromFile(String meshFile, String textureFile) {
        try {
            return loadFromFileAndTexture(meshFile, textureFile);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /* ========== PRIVATE ========== */
    private MeshLoader() {
    }

    private static Mesh loadFromFileAndTexture(String meshFile, String textureFile) throws NumberFormatException,
            IOException {
        List<Float> pointsList = new ArrayList<Float>();
        List<Float> colorsList = new ArrayList<Float>();
        List<Float> normalsList = new ArrayList<Float>();
        List<Float> texturesList = new ArrayList<Float>();
        List<Integer> pointsFacesList = new ArrayList<Integer>();
        List<Integer> texturesFacesList = new ArrayList<Integer>();
        List<Integer> normalsFacesList = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("res/mesh/" + meshFile + ".obj")));
        String line;
        while ((line = reader.readLine()) != null) {

            String[] split = line.trim().split("\\s++");

            for (int i = 0; i < split.length; ++i) {
                if (split[i].equals("v")) { // Loading vertex
                    pointsList.add(Float.parseFloat(split[i + 1]));
                    pointsList.add(Float.parseFloat(split[i + 2]));
                    pointsList.add(Float.parseFloat(split[i += 3]));
                } else if (split[i].equals("vt")) { // Loading textures
                    texturesList.add(Float.parseFloat(split[i + 1]));
                    texturesList.add(Float.parseFloat(split[i += 2]));
                } else if (split[i].equals("vn")) { // Loading normals
                    normalsList.add(Float.parseFloat(split[i + 1]));
                    normalsList.add(Float.parseFloat(split[i + 2]));
                    normalsList.add(Float.parseFloat(split[i += 3]));
                } else if (split[i].equals("f")) {

                    int j;
                    for (j = 1; i + j < split.length && !split[i + j].equals("f"); ++j) {
                        String[] subSplit = split[i + j].trim().split("/");
                        pointsFacesList.add(Integer.parseInt(subSplit[0]));
                        texturesFacesList.add(Integer.parseInt(subSplit[1]));
                        normalsFacesList.add(Integer.parseInt(subSplit[2]));
                    }
                    i += j - 1;
                }
            }
        }
        reader.close();

        return createMesh(textureFile, pointsList, colorsList, normalsList, texturesList, pointsFacesList,
                texturesFacesList, normalsFacesList);
    }

    private static Mesh createMesh(String textureFile, List<Float> pointsList, List<Float> colorsList,
            List<Float> normalsList, List<Float> texturesList, List<Integer> pointsFacesList,
            List<Integer> texturesFacesList, List<Integer> normalsFacesList) {
        float[] pointsData = floatToArray(pointsList);
        int[] pointsFacesData = intToArray(pointsFacesList);
        int[] texturesFacesData = intToArray(texturesFacesList);
        int[] normalsFacesData = intToArray(normalsFacesList);
        float[] colorsData = floatToArray(colorsList);
        float[] normalsData = floatToArray(normalsList);
        float[] textureDate = floatToArray(texturesList);

        FloatBuffer points = Buffers.newDirectFloatBuffer(pointsData.length);
        points.put(pointsData, 0, pointsData.length).rewind();

        FloatBuffer colors = Buffers.newDirectFloatBuffer(colorsData.length);
        colors.put(colorsData, 0, colorsData.length).rewind();

        FloatBuffer textures = Buffers.newDirectFloatBuffer(textureDate.length);
        textures.put(textureDate, 0, textureDate.length).rewind();

        IntBuffer pointsFaces = Buffers.newDirectIntBuffer(pointsFacesData.length);
        pointsFaces.put(pointsFacesData, 0, pointsFacesData.length).rewind();

        IntBuffer texturesFaces = Buffers.newDirectIntBuffer(texturesFacesData.length);
        texturesFaces.put(texturesFacesData, 0, texturesFacesData.length).rewind();

        IntBuffer normalsFaces = Buffers.newDirectIntBuffer(normalsFacesData.length);
        normalsFaces.put(normalsFacesData, 0, normalsFacesData.length).rewind();

        FloatBuffer normals = Buffers.newDirectFloatBuffer(normalsData.length);
        normals.put(normalsData, 0, normalsData.length).rewind();

        return new Mesh(textureFile, points, colors, normals, textures, pointsFaces, texturesFaces, normalsFaces);
    }

    private static int[] intToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static float[] floatToArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
}