package Builder;

/**
 * Effective Java에서 설명하는 Builder 패턴
 * @author kjbin0420 
 * - 객체의
 */

class Menu {
    private final int person;
    private final int menu1;
    private final int menu2;
    private final int menu3;
    private final int menu4;

    public static class MenuBuilder {
        private final int menu1;
        private final int menu2;
        private int person = 0;
        private int menu3 = 0;
        private int menu4 = 0;

        public MenuBuilder(int menu1, int menu2) {
            this.menu1 = menu1;
            this.menu2 = menu2;
        }

        public MenuBuilder person(int person) {
            this.person = person;
            return this;
        }

        public MenuBuilder menu3(int menu3) {
            this.menu3 = menu3;
            return this;
        }

        public MenuBuilder menu4(int menu4) {
            this.menu4 = menu4;
            return this;
        }

        public Menu build() {
            return new Menu(this);
        }
    }

    private Menu(MenuBuilder MenuBuilder) {
        person = MenuBuilder.person;
        menu1 = MenuBuilder.menu1;
        menu2 = MenuBuilder.menu2;
        menu3 = MenuBuilder.menu3;
        menu4 = MenuBuilder.menu4;
    }
}

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu.MenuBuilder(1, 2)
                .person(5)
                .menu3(4)
                .menu4(6)
                .build();
    }
}
