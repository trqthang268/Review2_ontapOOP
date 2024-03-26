package ra.run;

import ra.implement.CategoriesImplements;

import java.util.Scanner;

public class CategoriesManagement {
    static CategoriesImplements categoriesImplements = new CategoriesImplements();
    public void displayCategoriesMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("********************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật thông tin danh mục\n" +
                    "4. Xóa danh mục\n" +
                    "5. Cập nhật trạng thái danh mục\n" +
                    "6. Thoát\n");
            System.out.println("Nhập lựa chọn");
            switch (Byte.parseByte(scanner.nextLine())){
                case 1:
                    categoriesImplements.addNewCategories(scanner);
                    break;
                case 2:
                    categoriesImplements.displayAllCategories();
                    break;
                case 3:
                    categoriesImplements.updateCategories(scanner);
                    break;
                case 4:
                    categoriesImplements.deleteCategoris(scanner);
                    break;
                case 5:
                    categoriesImplements.updateStatus(scanner);
                    break;
                case 6:
                    return;
            }
        }
    }

}
