package me.iantapply;

import processing.core.PApplet;

public class Main extends PApplet {
    Segment tentacle;

    /**
     * Segment properties
     */
    static Integer numberOfSegments = 5;
    Integer segmentLength = 40;


    public static void main(String[] args) {
        PApplet.main("me.iantapply.Main");
    }

    public void settings() {
        // Size of window
        size(1200, 800);
    }

    public void setup() {
        // Leading segment following mouse (player)
        Segment current = new Segment(0, 0, segmentLength, 0, g);

        // Start index at 1 to account for leading segment
        // Creates a new segment that follows each other
        for (int i = 1; i < numberOfSegments; i++) {
            Segment next = new Segment(current, segmentLength, i, g);
            current.child = next;
            current = next;

        }
        tentacle = current;
    }

    public void draw() {
        // Background color
        background(51);

        // Follow, update, and show the leading segment
        tentacle.follow(mouseX, mouseY);
        tentacle.update();
        tentacle.show();

        // Make the other segments follow
        Segment next = tentacle.parent;
        while (next != null) {
            next.follow();
            next.update();
            next.show();
            next = next.parent;
        }
    }
}

