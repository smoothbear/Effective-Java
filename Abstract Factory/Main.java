package project2;


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

import java.util.Scanner;

/** 마우스 인터페이스 **/
interface Mouse {

}

/** 로지텍 마우스 클래스 **/
class LogitechMouse implements Mouse {
    LogitechMouse() {
        System.out.println("로지텍 마우스 생성\n");
    }
}

/** 커세어 마우스 클래스 **/
class CorsairMouse implements Mouse {
    CorsairMouse() {
        System.out.println("커세어 마우스 생성\n");
    }
}

public class Main {
    public static void main(String[] args) {
	    Mouse mouse = null;
        Scanner sc = new Scanner(System.in);
	    switch (sc.nextLine()) {
            case "Logitech":
                mouse = new LogitechMouse();
                break;
            case "Corsair":
                mouse = new CorsairMouse();
                break;
        }
    }
}
