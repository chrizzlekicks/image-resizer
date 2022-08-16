public interface Image {

    int sizeX();
    int sizeY();

    double contrast(Coordinate p1, Coordinate p2);

    void removeVPath(int[] path);

    void render();
}

