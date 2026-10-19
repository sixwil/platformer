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
        int[] lilGuy = { 5, (bgHeight-2)-5 };
        int fall = 1;
        int jump = 0;
        int movement = 0;

        boolean airBorne = true;

        GlobalListenerExample.main(args);

        String keptKey = null;

        int c = 0;
        while (true) {
            c++;
            // movement = 0;

            if (!GlobalListenerExample.keyKeeper.processed) {
                keptKey = GlobalListenerExample.keyKeeper.key;
                switch (keptKey) {
                    case "A":
                        movement = -1;
                        break;

                    case "D":
                        movement = +1;
                        break;

                    case "W":
                        if (!airBorne) {
                            airBorne = true;
                            jump = +2;
                        }
                        break;

                    default:
                        break;
                }
                // System.out.println(keptKey);
                GlobalListenerExample.keyKeeper.processed = true;
            }

            if (c % 5 == 0) {

                resetBackground(background, bgWidth, bgHeight);

                if (background.get(lilGuy[0] + movement + (bgWidth * lilGuy[1])).equals(tiles.air)) {
                    lilGuy[0] += movement;
                }
                movement = 0;

                // if (jump > 0){
                // }
                if (jump != 0) {
                    fall = (jump / Math.abs(jump));
                } else {
                    fall = 0;
                }

                if (airBorne) {
                    for (int j = 0; j < Math.abs(jump); j++)
                        if (background.get(lilGuy[0] + (bgWidth * (lilGuy[1] - fall))).equals(tiles.air)) {
                            lilGuy[1] -= (fall);

                        } else if (!background.get(lilGuy[0] + (bgWidth * (lilGuy[1] - 1))).equals(tiles.air)) {
                            jump = 0;
                        } else {
                            airBorne = false;
                        }
                    jump--;
                }

                // if (background.get(lilGuy[0] + (10 * (lilGuy[1] + fall))).equals(" ")) {
                // lilGuy[1] += fall;
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

        background.add(tiles.box);
        for (int i = 0+1; i < (bgWidth-1); i++) {
            background.add(tiles.box);
        }
        background.add(tiles.box);

        for (int y = 0+1; y < (bgHeight-1); y++) {
            background.add(tiles.box);
            // background.add(String.format(" %d ", y));
            for (int x = 0+1; x < bgWidth-1; x++) {
                background.add(tiles.air);
            }
            background.add(tiles.box);
        }

        background.add(tiles.box);
        for (int i = 0+1; i < (bgWidth-1); i++) {
            background.add(tiles.box);
        }
        background.add(tiles.box);

    }
}
