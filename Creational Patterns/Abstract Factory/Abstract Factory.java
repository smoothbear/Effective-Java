package project2;


import java.util.Scanner;

/**
 * @author kjbin0420
 * Abstract Factory
 * - 서로 관련이 있는 객체들을 통째로 묶어서 팩토리 클래스로 만들고,
 * 이들 팩토리를 조건에 따라 생성하도록 다시 팩토리를 만들어서 객체를 생성하는 패턴.
 *
 * Abstract Factory(추상 팩토리)는 다음의 경우에 사용한다.
 * - 객체가 생성되거나 구성/표현되는 방식과 무관하게 시스템을 독립적으로 만들고자 할 때.
 * - 여러 제품군 중 하나를 선택해서 시스템을 설정해야 하고 한번 구성한 제품을 다른 것으로 대체할 수 있을 때.
 * - 관련된 제품 객체들이 함께 사용되도록 설계되었고, 이 부분에 대한 제약이 외부에도 지켜지도록 하고 싶을 때.
 * - 제품에 대한 클래스 라이브러리를 제공하고, 그들의 구현이 아닌 인터페이스를 노출시키고 싶을 때.
 *
 * 장점
 * - 구체적인 클래스를 분리한다.
 * - 제품군을 쉽게 대체할 수 있도록 한다.
 * - 제품 사이의 일관성을 증진시킨다.
 * 단점
 * - 새로운 종류의 제품을 제공하기 어렵다.
 **/

/** 기존 방식 (Factory Method) **/

/**
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
**/

/**
 * 만약 키보드, 마우스 뿐만 아니라 다른 주변기기들을 같이 포장하게 된다면,
 * 각 팩토리 클래스를 정의해야 하고, 패키지팩토리(포장 클래스)에서는 여러개의 팩토리 객체들을 생성해야 한다.
 * 하지만, 결국 포장이 커세어이면 안에는 커세어 제품만 있어야 하고, 포장이 로지텍이면 로지텍 제품만 있어야 한다.
 * 따라서, 커세어이면 구성품이 모두 커세어가 되도록, 로지텍이면 구성품이 모두 커세어가 되도록 일관된 방식으로
 * 객체를 생성할 필요가 있다.
 * */

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

/* 마우스 인터페이스 */
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

// 포장 팩토리
interface CompanyFactory {
    public Keyboard createKeyboard();
    public Mouse createMouse();
}

class LogitechFactory implements CompanyFactory {

    @Override
    public Keyboard createKeyboard() {
        return new LogitechKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new LogitechMouse();
    }
}

/* 커세어 팩토리 클래스 */
class CorsairFactory implements CompanyFactory {

    @Override
    public Keyboard createKeyboard() {
        return new CorsairKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new CorsairMouse();
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

/* 포장 팩토리 */
class PackageFactory {
    public Package createPackage(String type) {
        CompanyFactory companyFactory = null;
        switch (type) {
            case "Logitech":
                companyFactory = new LogitechFactory();
                break;
            case "Corsair":
                companyFactory = new CorsairFactory();
                break;
        }
        return new Package(companyFactory.createKeyboard(), companyFactory.createMouse());
    }
}

/* 포장 팩토리에 호출 */
public class Main {
    public static void main(String[] args) {
        PackageFactory packageFactory = new PackageFactory();
        Package pack = packageFactory.createPackage("Logitech");
    }
}

/**
 * Abstract Factory Pattern 적용하기 전(Factory Method Pattern)에는 구성품마다 팩토리를 만들어서 어떤 객체를 형성했지만,
 * 패턴을 적용한 후에는 위와 같이 관련된 객체들을 한꺼번에 캡슐화하여 일관되게 객체를 생성하도록 할 수 있다.
 **/
