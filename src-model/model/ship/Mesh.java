package model.ship;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogamp.opengl.util.texture.Texture;

import game.architecture.Entity;

/**
 * Mesh of the {@link Ship}.
 */
public class Mesh extends Entity {

    private String textureFile;

    /* ========== PUBLIC ========== */
    public Mesh(String textureFile, FloatBuffer points, FloatBuffer colors, FloatBuffer normals, FloatBuffer textures,
            IntBuffer pointsFaces, IntBuffer texturesFaces, IntBuffer normalsFaces) {
        super(Mesh.class);

        this.textureFile = textureFile;

        this.points = points;
        this.colors = colors;
        this.normals = normals;
        this.textures = textures;
        this.pointsFaces = pointsFaces;
        this.normalsFaces = normalsFaces;
        this.texturesFaces = texturesFaces;
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private FloatBuffer points;
    @JsonIgnore
    private FloatBuffer colors;
    @JsonIgnore
    private FloatBuffer normals;
    @JsonIgnore
    private FloatBuffer textures;
    @JsonIgnore
    private IntBuffer pointsFaces;
    @JsonIgnore
    private IntBuffer normalsFaces;
    @JsonIgnore
    private IntBuffer texturesFaces;
    @JsonIgnore
    private Texture texture;

    private int vertexIndex;
    private int normalIndex;
    private int textureIndex;

    /* ========== PROPERTIES ========== */
    public String getTextureFile() {
        return textureFile;
    }

    public void setTextureFile(String textureFile) {
        this.textureFile = textureFile;
    }

    public FloatBuffer getPoints() {
        return points;
    }

    public void setPoints(FloatBuffer points) {
        this.points = points;
    }

    public FloatBuffer getColors() {
        return colors;
    }

    public void setColors(FloatBuffer colors) {
        this.colors = colors;
    }

    public FloatBuffer getNormals() {
        return normals;
    }

    public void setNormals(FloatBuffer normals) {
        this.normals = normals;
    }

    public FloatBuffer getTextures() {
        return textures;
    }

    public void setTextures(FloatBuffer textures) {
        this.textures = textures;
    }

    public IntBuffer getPointsFaces() {
        return pointsFaces;
    }

    public void setPointsFaces(IntBuffer pointsFaces) {
        this.pointsFaces = pointsFaces;
    }

    public IntBuffer getNormalsFaces() {
        return normalsFaces;
    }

    public void setNormalsFaces(IntBuffer normalsFaces) {
        this.normalsFaces = normalsFaces;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getVertexIndex() {
        return vertexIndex;
    }

    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
    }

    public int getNormalIndex() {
        return normalIndex;
    }

    public void setNormalIndex(int normalIndex) {
        this.normalIndex = normalIndex;
    }

    public int getTextureIndex() {
        return textureIndex;
    }

    public void setTextureIndex(int textureIndex) {
        this.textureIndex = textureIndex;
    }

    public IntBuffer getTexturesFaces() {
        return texturesFaces;
    }

    public void setTexturesFaces(IntBuffer texturesFaces) {
        this.texturesFaces = texturesFaces;
    }
}