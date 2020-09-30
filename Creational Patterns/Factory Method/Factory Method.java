/**
 * Factory Method (팩토리 메서드)
 *
 * - 팩토리 메서드
 */

// 키보드 인터페이스
interface Keyboard {}

// 로지텍 키보드 클래스
class LogitechKeyboard implements Keyboard {
    LogitechKeyboard() {
        System.out.println("로지텍 키보드 생성");
    }
}

// 커세어 키보드 클래스
class CorsairKeyboard implements  Keyboard {
    CorsairKeyboard() {
        System.out.println("커세어 키보드 생성");
    }
}

// 키보드 제작 클래스
class KeyboardFactory {
    public Keyboard createKeyboard(String name) {
        Keyboard keyboard = null;
        switch (name) {
            case "Logitech":
                keyboard = new LogitechKeyboard();
            case "Corsair":
                keyboard = new CorsairKeyboard();
        }
        return keyboard;
    }
}

// 마우스 인터페이스
interface Mouse {}

// 로지텍 마우스 클래스
class LogitechMouse implements Mouse {
    LogitechMouse() {
        System.out.println("로지텍 마우스 생성");
    }
}

// 커세어 마우스 클래스
class CorsairMouse implements Mouse {
    CorsairMouse() {
        System.out.println("커세어 마우스 생성");
    }
}

// 마우스 제작
class MouseFactory {
    public Mouse createMouse(String name) {
        Mouse mouse = null;
        switch (name) {
            case "Logitech":
                mouse = new LogitechMouse();
                break;
            case "Corsair":
                mouse = new CorsairMouse();
                break;
        }
        return mouse;
    }
}

// 포장 형식
class Package {
    Keyboard keyboard;
    Mouse mouse;

    Package(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }
}

// 패키지를 포장하는 클래스
class PackageFactory {
    public Package createPackage(String type) {
        KeyboardFactory keyboardFactory = new KeyboardFactory();
        MouseFactory mouseFactory = new MouseFactory();

        return new Package(keyboardFactory.createKeyboard(type), mouseFactory.createMouse(type));
    }
}

// 포장 팩토리에 호출
class Client {
    public void buying() {
        PackageFactory factory = new PackageFactory();
        Package pack = factory.createPackage("Corsair");
    }
}