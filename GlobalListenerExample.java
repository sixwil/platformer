//  ↓   quarantine zone for vibe-code   ↓   //

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalListenerExample implements NativeKeyListener {

    public void nativeKeyPressed(NativeKeyEvent e) {
        String key = NativeKeyEvent.getKeyText(e.getKeyCode());

        // System.out.println(key);

        keyKeeper.key = key;
        keyKeeper.processed = false;

        // System.out.println(keyKeeper.key);
    }

    public static void registrationStuff() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        GlobalScreen.addNativeKeyListener(new GlobalListenerExample());
    }

    public static void main(String[] args) {
        registrationStuff();
    }

    public static class KeyKeeperClass {
        boolean processed = true;
        String key;
    }

    public static final KeyKeeperClass keyKeeper = new KeyKeeperClass();
}
//  ↑   quarantine zone for vibe-code  ↑   //