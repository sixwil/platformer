import java.util.ArrayList;
// import java.awt.event.*;

public class platform {

    public static void main(String[] args) {

        ArrayList<String> background = new ArrayList<>();
        int[] lilGuy = { 5, 8 };
        int fall = 1;
        int jump = 0;
        int movement = 0;

        boolean airBorne = false;

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
                            jump = +3;
                        }
                        break;

                    default:
                        break;
                }
                // System.out.println(keptKey);
                GlobalListenerExample.keyKeeper.processed = true;
            }

            if (c % 5 == 0) {

                resetBackground(background);

                if (background.get(lilGuy[0] + movement + (10 * (lilGuy[1]))).equals("   ")) {
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
                        if (background.get(lilGuy[0] + (10 * (lilGuy[1] - fall))).equals("   ")) {
                            lilGuy[1] -= (fall);
                        } else if (!background.get(lilGuy[0] + (10 * (lilGuy[1] - 1))).equals("   ")) {
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

                background.set(lilGuy[0] + (10 * lilGuy[1]), " @ ");

                displayBackground(background);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void displayBackground(ArrayList<String> background) {
        System.out.println("\n".repeat(9));
        for (int y = 0; y < background.size() / 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(background.get(x + (10 * y)));
            }
            System.out.println();
        }
    }

    public static void resetBackground(ArrayList<String> background) {
        background.clear();

        background.add(" + ");
        for (int i = 1; i < 9; i++) {
            background.add(" - ");
        }
        background.add(" + ");

        for (int y = 1; y < 9; y++) {
            background.add(" : ");
            for (int x = 1; x < 9; x++) {
                background.add(" " + " " + " ");
            }
            background.add(" : ");
        }

        background.add(" + ");
        for (int i = 1; i < 9; i++) {
            background.add(" - ");
        }
        background.add(" + ");

    }
}
