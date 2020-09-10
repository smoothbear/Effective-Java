package JavaBeans;

/**
 * JavaBeans Pattern(자바빈 패턴)
 * @author kjbin0420
 * - 자바빈 패턴은 인자없는 생성자를 호출하여 객체부터 만든 다음, 설정 메서드(setter)들을 호출하여 필수, 선택 필드의 값을 채우는 방식이다.
 *
 * 단점
 * - 점층적 생성자 패턴의 단점인 가독성 면에서는 좋아졌지만 코드의 양이 증가한다.
 * - 1회 함수 호출로 객체 생성을 끝낼 수 없으므로, 객체 일관성이 일시적으로 깨질 수 있다.
 * - 변경 불가능 클래스를 만들 수 없다.
 */

class Name {
    private String name = "정보 없음";
    private int age = 0;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Name name = new Name();
        name.setAge(17);
        name.setName("dddd");
    }
}
