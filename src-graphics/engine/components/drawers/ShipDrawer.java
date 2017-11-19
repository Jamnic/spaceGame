package engine.components.drawers;

import engine.utils.LightLoader;
import engine.utils.TextureLoader;
import game.architecture.Drawer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import model.ship.Mesh;
import model.ship.Position;
import model.ship.Ship;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

/**
 * Component responsible for drawing {@link Ship}.
 */
public final class ShipDrawer extends Drawer<Ship> {

    private FloatBuffer points = Buffers.newDirectFloatBuffer(0);
    private FloatBuffer normals = Buffers.newDirectFloatBuffer(0);
    private FloatBuffer textures = Buffers.newDirectFloatBuffer(0);

    private IntBuffer faces = Buffers.newDirectIntBuffer(0);

    /* ========== PROTECTED ========== */
    protected void drawDrawable(GL2 gl, Ship ship) {

        if (ship != null) {

            Position position = ship.getPosition();

            translateWithCoords(gl, position.getCoords());

            gl.glRotated(-90, 0, 1, 0); // TODO obrót modelu

            gl.glRotated(position.getRotationX(), 0, 1, 0);
            gl.glRotated(position.getRotationY(), 1, 0, 0);

            double size = ship.getSize();
            gl.glScaled(size, size, size);

            LightLoader.planetaryLight(gl);

            Texture texture = ship.getTexture();
            if (texture == null) {
                texture = initialize(gl, ship.getMesh());
            }

            gl.glVertexPointer(3, GL.GL_FLOAT, 0, points);
            gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, textures);
            gl.glNormalPointer(GL.GL_FLOAT, 0, normals);

            texture.enable(gl);
            texture.bind(gl);

            gl.glDrawElements(GL2.GL_TRIANGLES, faces.capacity(), GL2.GL_UNSIGNED_INT, faces);

            texture.disable(gl);
        }
    }

    private Texture initialize(GL2 gl, Mesh mesh) {
        Texture texture;
        mesh.setTexture(texture = TextureLoader.getTexture(gl, mesh.getTextureFile()));

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);

        IntBuffer pointsFaces = mesh.getPointsFaces();
        IntBuffer normalsFaces = mesh.getNormalsFaces();
        IntBuffer textureFaces = mesh.getTexturesFaces();

        int[] facesArray = new int[pointsFaces.capacity()];

        float[] pointsArray = new float[pointsFaces.capacity() * 3];
        float[] texturesArray = new float[pointsFaces.capacity() * 2];
        float[] normalsArray = new float[pointsFaces.capacity() * 3];

        int counter = 0;

        for (int i = 0; i < pointsFaces.capacity(); ++i) {

            facesArray[i] = counter++;

            // Vertex
            int vertex1 = pointsFaces.get(i) * 3 - 3;
            int vertex2 = pointsFaces.get(i) * 3 - 2;
            int vertex3 = pointsFaces.get(i) * 3 - 1;

            pointsArray[i * 3 + 0] = mesh.getPoints().get(vertex1);
            pointsArray[i * 3 + 1] = mesh.getPoints().get(vertex2);
            pointsArray[i * 3 + 2] = mesh.getPoints().get(vertex3);

            // Texture
            int texture1 = textureFaces.get(i) * 2 - 2;
            int texture2 = textureFaces.get(i) * 2 - 1;

            texturesArray[i * 2 + 0] = mesh.getTextures().get(texture1);
            texturesArray[i * 2 + 1] = mesh.getTextures().get(texture2);

            // Normal
            int normal1 = normalsFaces.get(i) * 3 - 3;
            int normal2 = normalsFaces.get(i) * 3 - 2;
            int normal3 = normalsFaces.get(i) * 3 - 1;

            normalsArray[i * 3 + 0] = mesh.getNormals().get(normal1);
            normalsArray[i * 3 + 1] = mesh.getNormals().get(normal2);
            normalsArray[i * 3 + 2] = mesh.getNormals().get(normal3);
        }

        faces = Buffers.newDirectIntBuffer(facesArray);
        points = Buffers.newDirectFloatBuffer(pointsArray);
        textures = Buffers.newDirectFloatBuffer(texturesArray);
        normals = Buffers.newDirectFloatBuffer(normalsArray);

        return texture;
    }

    // private float[] addArrays(FloatBuffer buffer1, FloatBuffer buffer2) {
    // float array[] = new float[buffer1.capacity() + buffer2.capacity()];
    //
    // int i = 0;
    //
    // for (i = 0; i < buffer2.capacity(); ++i) {
    // array[i] = buffer2.get(i);
    // }
    //
    // for (int j = 0; j < buffer1.capacity(); ++j) {
    // array[i + j] = buffer1.get(j);
    // }
    //
    // return array;
    // }
}