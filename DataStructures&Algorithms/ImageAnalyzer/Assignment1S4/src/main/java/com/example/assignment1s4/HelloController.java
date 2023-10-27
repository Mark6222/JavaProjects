package com.example.assignment1s4;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static javafx.application.Platform.exit;

public class HelloController {
    public int FindUnionXY = 0;
    public MenuBar fileButton;
    public BarChart histogram;
    private String filepath;
    @FXML
    public Label width;
    public Label height;
    public Label numOfStars;
    public Slider brightness;
    public Slider saturation;
    public Slider hue;
    public Slider colour;
    public Slider noise;
    public Image defaultImage;
    public Image viewImage;
    public ImageView imageView;
    public Pane circles;
    public Pane Labelling;

    public boolean circle = false;
    public boolean labels = false;
    public boolean ifCircleRun = false;

    int arrayLinking[];
    int arrayRoots[];
    int arrayOfRoots[];
    int arrayOfRootsPixelSize[];

    public void initialize() throws IOException {
        circles.setOpacity(0);
        Labelling.setOpacity(0);
        //filepath = "C:\\Users\\marky\\Downloads\\stars.jpg";
        //OnFileButton();
        System.out.println(filepath);
        if (viewImage != null) {
            int width = (int) viewImage.getWidth();
            int height = (int) viewImage.getHeight();
            int[] blue = new int[256];
            int[] red = new int[256];
            PixelReader pr = viewImage.getPixelReader();
            XYChart.Series series1 = new XYChart.Series();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color col = pr.getColor(x, y);
                    int blu = (int) (col.getBlue() * 255);
                    blue[blu] = blue[blu] + 1;

                    int re = (int) (col.getRed() * 255);
                    red[re] = red[re] + 1;

                    int gre = (int) (col.getGreen() * 255);
                    blue[blu] = blue[gre] + 1;

                }
            }
            for (int i = 0; i < 255; i++) {
                series1.getData().add(new XYChart.Data("" + i, blue[i]));
            }
            histogram.getData().add(series1);


            Tooltip tooltip = new Tooltip("Your tooltip text");
            Tooltip.install(circles, tooltip);
            tooltip.setShowDelay(Duration.millis(0));
            Labelling.setOnMouseMoved(event -> {
                double x;
                if (ifCircleRun) {
                    x = event.getX() + 45;
                } else {
                    x = event.getX();
                }
                double y = event.getY();
                int index = (int) ((y * width) + x);
                if (arrayRoots != null) {
                    int num = arrayRoots[index];
                    int root = 0;
                    if (num != -1) {
                        int n = 0;
                        while (num != root) {
                            root = arrayOfRoots[n];
                            n = n + 1;
                        }
                        int numOfPixels = numOfPixels(root);
                        tooltip.setText("Celestial object number: " + (n - 1) + "\n"
                                + "Estimated Size (pixel units): " + numOfPixels + "\n"
                                + "Estimated sulpher: " + sulpher(root) + "\n"
                                + "Estimated hydrogen: " + hydrogen(root) + "\n"
                                + "Estimated oxygen: " + oxygen(root) + "\n");
                    }
                }
                tooltip.show(circles, x + 500, y + 150);
            });
        }
    }

    public void circles() {
        if (circle) {
            circles.setOpacity(0);
            circle = false;
        } else {
            circles.setOpacity(1);
            circle = true;
        }
    }

    public void labelling() {
        if (labels) {
            Labelling.setOpacity(0);
            labels = false;
        } else {
            Labelling.setOpacity(1);
            labels = true;
        }
    }

    public double sulpher(int root){
        double red = 0;
        int NumOfRoot = 0;
        PixelReader pixelReader = defaultImage.getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        for(int i = 0; i < arrayRoots.length; i++){
            int stored = arrayRoots[i];
            if(stored == root){
                NumOfRoot = NumOfRoot + 1;
                int y = i / width;
                int x = i % width;
                Color color = pixelReader.getColor(x, y);
                red = red + color.getRed();
            }
        }
        return red/NumOfRoot;
    }

    public double hydrogen(int root){
        double green = 0;
        int NumOfRoot = 0;
        PixelReader pixelReader = defaultImage.getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        for(int i = 0; i < arrayRoots.length; i++){
            int stored = arrayRoots[i];
            if(stored == root){
                NumOfRoot = NumOfRoot + 1;
                int y = i / width;
                int x = i % width;
                Color color = pixelReader.getColor(x, y);
                green = green + color.getGreen();
            }
        }
        return green/NumOfRoot;
    }

    public double oxygen(int root){
        double blue = 0;
        int NumOfRoot = 0;
        PixelReader pixelReader = defaultImage.getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        for(int i = 0; i < arrayRoots.length; i++){
            int stored = arrayRoots[i];
            if(stored == root){
                NumOfRoot = NumOfRoot + 1;
                int y = i / width;
                int x = i % width;
                Color color = pixelReader.getColor(x, y);
                blue = blue + color.getBlue();
            }
        }
        return blue/NumOfRoot;
    }

    public void OnFileButton() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.webp"));
        File selectedFile = fileChooser.showOpenDialog(fileButton.getScene().getWindow());
        if (selectedFile != null) {
           filepath = selectedFile.getAbsolutePath();
        }
        InputStream stream = new FileInputStream(filepath);
        Image image = new Image(stream);
        viewImage = image;
        defaultImage = image;
        imageView.setImage(image);
        height.setText(image.getHeight() + "");
        width.setText(image.getWidth() + "");
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        circles.setPrefWidth(width);
        circles.setPrefHeight(height);
        Labelling.setPrefWidth(width);
        Labelling.setPrefHeight(height);
    }

    public void initial() throws IOException {
        initialize();
    }

    public void exit1() {
        exit();
    }

    public void toBlackWhiteScale() {
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage grayImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();
                double grayLevel = (red + green + blue) / 3;
                double num = colour.getValue() / 255;
                Color blackOrWhite = null;
                if (grayLevel > num) {
                    blackOrWhite = Color.WHITE;
                } else if (grayLevel < num) {
                    blackOrWhite = Color.BLACK;
                } else if (0 == num) {
                    blackOrWhite = Color.WHITE;
                } else if (1 == num) {
                    blackOrWhite = Color.BLACK;
                }
                grayImage.getPixelWriter().setColor(x, y, blackOrWhite);
            }
        }
        imageView.setImage(grayImage);
    }

    public void UnionFind() {
        int pixel = -1;
        Image ViewImage = imageView.getImage();
        PixelReader pr = ViewImage.getPixelReader();
        int width = (int) ViewImage.getWidth();
        int height = (int) ViewImage.getHeight();
        arrayLinking = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = pixel + 1;
                Color col = pr.getColor(x, y);
                if (col.getRed() == 1) {
                    int find = Find(x, y, false, 0); //find while return 0 if no other white pixel around it is already in the array
                    if (find == 0) {        //if it is in the array it will set it to that pixels number which should be the roots number
                        int num = (y * width) + x;
                        arrayLinking[pixel] = num;
                    } else {
                        arrayLinking[pixel] = find;
                    }
                } else {
                    arrayLinking[pixel] = -1;
                }
            }
        }
        System.out.println("done");
        union();
    }
    public int Find(int x, int y, boolean union, int root1) {
        int stop = 0;
        int findRoot = 0;
        int Join = 0;
        Image ViewImage = imageView.getImage();
        PixelReader pr = ViewImage.getPixelReader();
        int width = (int) ViewImage.getWidth();
        int height = (int) ViewImage.getWidth();
        x = x - 1;
        y = y + 1;
        boolean direction = false;
        boolean type = true;
        boolean found = false;
        outerloop:
        for (int i = 0; i < 4; i++) {
            if (stop == 4) {
                break outerloop;
            }
            if (i == 2) {
                type = false;
            }
            if (direction) {
                direction = false;
            } else {
                direction = true;
            }
            for (int a = 0; a < 2; a++) {

                stop = stop + 1;
                if (direction & type) {
                    y = y - 1;
                } else if (direction & !type) {
                    y = y + 1;
                } else if (!direction & type) {
                    x = x + 1;
                } else if (!direction & !type) {
                    x = x - 1;
                }
                if (x >= 0 & y >= 0 & x < width & y < height) {
                    Color col = pr.getColor(x, y);
                    if (col.getRed() == 1 & arrayLinking != null) {
                        int value = (y * width) + x;
                        int num = arrayLinking[value];  // this is linking the first white pixel
                        if (num != -1 & num != 0 & found == false & union == false) {
                            found = true;
                            Join = value;
                        }

                        if (num != -1 & num != 0 & union) {
                            int XY = (y * width) + x;
                            FindUnionXY = (y * width) + x; // this sets the int at the top because this class only returns the root and i need the xy aswell
                            int slot = arrayLinking[XY];
                            int s = 0;
                            while (slot != s) {
                                s = slot;
                                slot = arrayLinking[s];
                            }
                            if (slot != root1) {
                                findRoot = slot;
                                break outerloop;
                            }
                        }
                    }
                }
            }
        }
        return union ? findRoot : Join;
    }

    public void union() {
        Image ViewImage = imageView.getImage();
        int width = (int) ViewImage.getWidth();
        for (int i = 0; i < arrayLinking.length; i++) {
            int index = arrayLinking[i]; // this is basically root1
            if (index != -1) {
                int y = index / width; // these are for the find to go around the index
                int x = index % width;
                if (index != i) {
                    int a = 0;
                    while (index != a) {  //this gets the root of this pixel
                        a = index;
                        index = arrayLinking[a];
                    }
                }
                int find = Find(x, y, true, index);
                if (find != 0 & index != find) {
                    int root1 = index;
                    int root2 = find;
                    int x1 = root1 % width, y1 = root1 / width, x2 = root2 % width, y2 = root2 / width;
                    if (y1 > y2) {
                        arrayLinking[root1] = FindUnionXY;
                    } else if (y1 == y2) {
                        if (x1 > x2) {
                            arrayLinking[root1] = FindUnionXY;
                        } else {
                            arrayLinking[root2] = i;
                        }
                    } else {
                        arrayLinking[root2] = i;
                    }
                }
            }
        }
        rootStars();
        arrayOfOnlyRoots();
    }

    public void rootStars() {//this method basicaly adds the root of each star and stores it in each pixels index in the array
        Image ViewImage = imageView.getImage();
        int width = (int) ViewImage.getWidth();
        int height = (int) ViewImage.getHeight();
        arrayRoots = new int[width * height];
        for (int i = 0; i < arrayLinking.length; i++) {
            int stored = arrayLinking[i];
            if (stored != 0 & stored != -1) {
                int a = 0;
                while (stored != a) {  //this gets the root of this pixel by going through each index and getting the root that linked to the next until it get to the final root
                    a = stored;
                    stored = arrayLinking[a];
                }
                arrayRoots[i] = stored; //at the end we set that pixel to the root
            } else {
                arrayRoots[i] = -1;
            }
        }
    }

    public void arrayOfOnlyRoots() {//this method makes the arrayOfRoots store all of the roots of each star
        int num = 0;
        for (int i = 0; i < arrayLinking.length; i++) {
            int Stored = arrayLinking[i];
            if (Stored == i) {
                num = num + 1;
            }
        }
        int arrayOfRootsNum = 0;
        arrayOfRoots = new int[num];
        arrayOfRootsPixelSize = new int[num];
        for (int a = 0; a < arrayLinking.length; a++) {
            int Stored = arrayLinking[a];
            if (Stored == a) {
                arrayOfRoots[arrayOfRootsNum] = Stored;
                arrayOfRootsPixelSize[arrayOfRootsNum] = numOfPixels(Stored);
                arrayOfRootsNum = arrayOfRootsNum + 1;
            }
        }
        arrayOfRoots();
    }

    public void arrayOfRoots() { //this method rearanges the arrayOfRoots array so the the root of the largest pixelis at the start
        int arrayofActualRoots[] = new int[arrayOfRoots.length];
        int largestPreviousStar = 0;
        int largestStar = 0;
        int largestPixelSize = 0;
        boolean notLargest = false;
        for (int i = 0; i < arrayOfRoots.length; i++) {
            int previousStored = -1;
            for(int a = 0; a < arrayOfRoots.length; a++){
                boolean inArray = false;
                for(int c = 0; c < arrayofActualRoots.length; c++){
                    int stored = arrayofActualRoots[c];
                    if(stored == arrayOfRoots[a]){
                        inArray = true;
                    }
                }
                int stored = arrayOfRootsPixelSize[a];
                int root = arrayOfRoots[a];
                if(stored > previousStored & notLargest == false){
                    previousStored = stored;
                    largestStar = root;
                }
                if(stored > previousStored & stored < largestPixelSize & notLargest){
                    previousStored = stored;
                    largestStar = root;
                }else if(stored == largestPixelSize & inArray == false & notLargest){
                    previousStored = stored;
                    largestStar = root;
                }
            }
            notLargest = true;
            largestPixelSize = numOfPixels(largestStar);
            arrayofActualRoots[i] = largestStar;
        }
        arrayOfRoots = arrayofActualRoots;
        for (int b = 0; b < arrayOfRoots.length; b++) {
            System.out.println("index = " + b + " star root = " + arrayOfRoots[b] + " size=" + numOfPixels(arrayOfRoots[b]));
        }
        numOfStars.setText("Number of stars" + numOfStars());
    }

    public void FindList() {
        for (int i = 0; i < arrayLinking.length; i++) {
            int a = arrayLinking[i];
            System.out.println("index=" + i + " root number=" + a);
        }
    }

    public void noiseManagement() {
        System.out.println(noise.getValue());
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();
        WritableImage noiseImage = new WritableImage(width, height);
        int array[] = new int[arrayRoots.length];
        for (int i = 0; i < arrayRoots.length; i++) {
            boolean ignore;
            int y = i / width;
            int x = i % width;
            int stored = arrayRoots[i];
            int pixelSize;
            if (stored != -1) {
                pixelSize = numOfPixels(stored);
                if (pixelSize < noise.getValue()) {
                    ignore = true;
                } else {
                    ignore = false;
                }
                if (ignore == false) {
                    noiseImage.getPixelWriter().setColor(x, y, Color.WHITE);
                } else {
                    noiseImage.getPixelWriter().setColor(x, y, Color.BLACK);
                }
            } else {
                noiseImage.getPixelWriter().setColor(x, y, Color.BLACK);
            }
            imageView.setImage(noiseImage);
        }
    }

    public int numOfPixels(int root) {
        int numOfPixels = 0;
        for (int i = 0; i < arrayRoots.length; i++) {
            int stored = arrayRoots[i];
            if (stored == root) {
                numOfPixels = numOfPixels + 1;
            }
        }
        return numOfPixels;
    }

    public void circlesAndLabelling() throws IOException {
        ifCircleRun = true;
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        for (int array = 0; array < arrayOfRoots.length; array++) {
            int minY = 0, minX = 0, maxY = 0, maxX = 0;
            int stored = arrayOfRoots[array];
            for (int i = 0; i < width * height; i++) {
                int y = i / width;
                int x = i % width;
                int num = arrayRoots[i];
                if (num == stored) {
                    if (minY == 0 & maxY == 0) {
                        minY = y;
                        maxY = y;
                    }
                    if (minX == 0 & maxX == 0) {
                        maxX = x;
                        minX = x;
                    }
                    if (y > maxY) {
                        maxY = y;
                    }
                    if (y < minY) {
                        minY = y;
                    }
                    if (x > maxX) {
                        maxX = x;
                    }
                    if (x < minX) {
                        minX = x;
                    }

                }
            }
            int centerX = (maxX + minX) / 2;
            int centerY = (maxY + minY) / 2;
            int radius = maxX - centerX;
            int radius2 = maxY - centerY;
            radius = radius + radius / 3;
            radius2 = radius2 + radius2 / 3;
            Circle circle = new Circle();
            circle.setLayoutX(centerX);
            circle.setLayoutY(centerY);
            if (radius > radius2) {
                circle.setRadius(radius);
            } else {
                circle.setRadius(radius2);
            }
            circle.setStrokeWidth(1);
            circle.setStroke(Color.BLUE);
            circle.setFill(Color.TRANSPARENT);
            circles.getChildren().add(circle);
            Text text = new Text();
            text.setText(array + "");
            text.setLayoutX(maxX);
            text.setLayoutY(minY);
            text.setStroke(Color.GOLD);
            text.setFont(Font.font("verdana", 10));
            Labelling.getChildren().add(text);
        }
    }

    public void List2() {
        for (int i = 0; i < arrayRoots.length; i++) {
            int a = arrayRoots[i];
            System.out.println("index=" + i + " root number=" + a);
        }
    }

    public int numOfStars() {
            return arrayOfRoots != null ? arrayOfRoots.length : 0;
    }

    public void colourTheStars() {
        Color colour;
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        WritableImage ColourStars = new WritableImage(width, height);
        for (int c = 0; c < width * height; c++) {
            int y = c / width; // these are for the find to go around the index
            int x = c % width;
            ColourStars.getPixelWriter().setColor(x, y, Color.BLACK);
        }
        for (int array = 0; array < arrayOfRoots.length; array++) {
            int StarRoot = arrayOfRoots[array];
            Random random = new Random();
            float red = random.nextFloat();
            float green = random.nextFloat();
            float blue = random.nextFloat();
            Color color = new Color(red, green, blue, 1);
            colour = color;
            for (int i = 0; i < arrayRoots.length; i++) {
                int y = i / width; // these are for the find to go around the index
                int x = i % width;
                int stored = arrayRoots[i];
                if (StarRoot == stored) {
                    //System.out.println("COLOUR: i=" + i + " stored=" + stored + " =" + StarRoot);
                    ColourStars.getPixelWriter().setColor(x, y, colour);
                }
            }
        }
        imageView.setImage(ColourStars);
    }

    public void getBlueChannel() {
        PixelReader pr = viewImage.getPixelReader();
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        WritableImage result = new WritableImage(width, height);
        PixelWriter pw = result.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color col = pr.getColor(x, y);
                pw.setColor(x, y, new Color(0, 0, col.getBlue(), 1.0));
            }
        }
        imageView.setImage(result);
    }

    public void getRedChannel() {
        PixelReader pixel = viewImage.getPixelReader();
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        WritableImage result = new WritableImage(width, height);
        PixelWriter pw = result.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color col = pixel.getColor(x, y);
                pw.setColor(x, y, new Color(col.getRed(), 0, 0, 1.0));
            }
        }
        imageView.setImage(result);
    }

    public void getGreenChannel() {
        PixelReader pixel = viewImage.getPixelReader();
        int width = (int) viewImage.getWidth();
        int height = (int) viewImage.getHeight();
        WritableImage result = new WritableImage(width, height);
        PixelWriter pw = result.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color col = pixel.getColor(x, y);
                pw.setColor(x, y, new Color(0, col.getGreen(), 0, 1.0));
            }
        }
        imageView.setImage(result);
    }

    public void brightnessSlider() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(brightness.getValue());
        imageView.setEffect(colorAdjust);
    }

    public void saturationSlider() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(saturation.getValue());
        imageView.setEffect(colorAdjust);
    }

    public void hueSlider() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(hue.getValue());
        imageView.setEffect(colorAdjust);
    }

    public void toGrayScale() {
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage grayImage = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pixelReader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();
                double grayLevel = (red + green + blue) / 3;
                Color grey = new Color(grayLevel, grayLevel, grayLevel, grayLevel);
                grayImage.getPixelWriter().setColor(x, y, grey);
            }
        }
        imageView.setImage(grayImage);
    }


}