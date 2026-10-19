import java.util.ArrayList;
// import java.awt.event.*;

public class platform {

    public class tiles {
        static String box = "# ";
        static String air = "+ ";
        static String lilGuySprite = "@ ";
    }

    public static void main(String[] args) {

        ArrayList<String> background = new ArrayList<>();
        int bgWidth = 10;
        int bgHeight = 20;
        int[] lilGuy = { 5, (bgHeight - 2) - 5 };
        int yMovementDirection = 1;
        int yMovementSpeed = 0;
        int xMovementDirection = 0;

        boolean airBorne = true;

        GlobalListenerExample.main(args);

        String keptKey = null;

        int tickCount = 0;
        while (true) {
            tickCount++;
            // xMovementDirection = 0;

            if (!GlobalListenerExample.keyKeeper.processed) {
                keptKey = GlobalListenerExample.keyKeeper.key;
                switch (keptKey) {
                    case "A":
                        xMovementDirection = -1;
                        break;

                    case "D":
                        xMovementDirection = +1;
                        break;

                    case "W":
                        if (!airBorne) {
                            // airBorne = true;
                            yMovementSpeed = +3;
                        }
                        break;

                    default:
                        break;
                }
                // System.out.println(keptKey);
                GlobalListenerExample.keyKeeper.processed = true;
            }

            if (tickCount % 5 == 0) {

                resetBackground(background, bgWidth, bgHeight);

                if (background.get(lilGuy[0] + xMovementDirection + (bgWidth * lilGuy[1])).equals(tiles.air)) {
                    lilGuy[0] += xMovementDirection;
                }
                xMovementDirection = 0;

                // if (yMovementSpeed > 0){
                // }
                if (yMovementSpeed != 0) {
                    yMovementDirection = (yMovementSpeed / Math.abs(yMovementSpeed));
                } else {
                    yMovementDirection = 0;
                }

                
                if (airBorne || yMovementDirection > 0) {
                    for (int j = 0; j < Math.abs(yMovementSpeed); j++)
                        if (background.get(lilGuy[0] + (bgWidth * (lilGuy[1] - yMovementDirection)))
                            .equals(tiles.air)) {
                        lilGuy[1] -= (yMovementDirection);
                        
                    } else {
                            // airBorne = false;
                            yMovementSpeed = +1;
                            break;
                        }
                        yMovementSpeed--;
                    }
                    
                    if (background.get(lilGuy[0] + (bgWidth * (lilGuy[1] + 1))).equals(tiles.air)) {
                        airBorne = true;
                    } else { airBorne = false; }

                // else {yMovementSpeed = -1;}

                // else {
                // airBorne = false;
                // yMovementSpeed = 0;
                // }
                // if (background.get(lilGuy[0] + (10 * (lilGuy[1] +
                // yMovementDirection))).equals(" ")) {
                // lilGuy[1] += yMovementDirection;
                // }
                // else {
                // airBorne = true;
                // }

                background.set(lilGuy[0] + (bgWidth * lilGuy[1]), tiles.lilGuySprite);

                displayBackground(background, bgWidth, bgHeight);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void displayBackground(ArrayList<String> background, int bgWidth, int bgHeight) {
        System.out.println("\n".repeat(9));

        for (int y = 0; y < bgHeight; y++) {
            for (int x = 0; x < bgWidth; x++) {
                System.out.print(background.get(x + (bgWidth * y)));
            }
            System.out.println();
        }
    }

    public static void resetBackground(ArrayList<String> background, int bgWidth, int bgHeight) {
        background.clear();

        ///     ROOF    ///
        background.add(tiles.box);
        for (int i = 0 + 1; i < bgWidth - 1; i++) {
            background.add(tiles.box);
        }
        background.add(tiles.box);
        ///     roof    ///

        for (int y = 0 + 1; y < bgHeight - 1; y++) {
            background.add(tiles.box);
            for (int x = 0 + 1; x < bgWidth - 1; x++) {

                // if (Math.random() < 0.1) {background.add(tiles.box);}
                // // System.out.println(Math.random());
                // else {background.add(tiles.air);}

                if (x == 5 && y == 13) {
                    background.add(tiles.box);
                } else {
                    background.add(tiles.air);
                }

            }
            background.add(tiles.box);
        }

        ///     FLOOR   ///
        background.add(tiles.box);
        for (int i = 0 + 1; i < bgWidth - 1; i++) {
            background.add(tiles.box);
        }
        background.add(tiles.box);
        ///     floor   ///

    }
}
