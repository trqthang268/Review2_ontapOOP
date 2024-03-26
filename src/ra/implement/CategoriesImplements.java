package ra.implement;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Arrays;
import java.util.Scanner;

import static ra.entity.Categories.currentId;
import static ra.implement.ProductImplements.products;


public class CategoriesImplements {
    public static Categories[] categories = new Categories[100];

    Scanner scanner = new Scanner(System.in);

    //1. Nhập thông tin các danh mục
    public void addNewCategories(Scanner scanner) {
        System.out.println("Nhập số lượng danh mục muốn thêm mới");
        byte countAddNewCategories = Byte.parseByte(scanner.nextLine());
        for (int i = 0; i < countAddNewCategories; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            Categories newCategories = new Categories();
            newCategories.inputData(scanner, categories);
            for (int j = 0; j < categories.length; j++) {
                if(categories[i] == null){
                    categories[i] = newCategories;
                    break;
                }
            }
        }
        System.out.println("Đã thêm mới thành công");
    }

    //2. Hiển thị thông tin các danh mục
    public void displayAllCategories() {
        System.out.println("Thông tin các danh mục :");
        if (checkListNull()){
            return;
        }
        for (Categories i : categories) {
            if (i != null) {
                i.displayData();
                System.out.println("----------------------------------------------");
            }
        }
    }

    //3. Cập nhật thông tin danh mục
    public void updateCategories(Scanner scanner) {
        if (checkListNull()){
            return;
        }
        System.out.println("Nhập mã danh mục cần sửa");
        int updateCategoriesId = Integer.parseInt(scanner.nextLine());
        int updateCategoriesIdIndex = findIndexCategoryById(updateCategoriesId);
        if (updateCategoriesIdIndex > -1) {
            System.out.println("Thông tin cũ");
            categories[updateCategoriesIdIndex].displayData();
            int choice;
            do {
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trạng thái");
                System.out.println("4. thoát");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên mới");
                        categories[updateCategoriesIdIndex].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập mô tả mới");
                        categories[updateCategoriesIdIndex].setDescriptions(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập trạng thái mới");
                        categories[updateCategoriesIdIndex].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1 đến 4");
                }
            }while (choice!=4);
        }else{
            System.err.println("Danh mục không tồn tại");
        }
    }

    //4. Xóa danh mục
    public void deleteCategoris(Scanner scanner) {
        if (checkListNull()){
            return;
        }
//        Người dùng nhập mã danh mục cần xóa
        System.out.println("Nhập mã danh mục cần xóa");
        int deleteCategoriesId = Integer.parseInt(scanner.nextLine());
        int deleteCategoriesIdIndex = findIndexCategoryById(deleteCategoriesId);
        if (deleteCategoriesIdIndex == -1){
            System.err.println("Không tìm thấy danh mục yêu cầu");
            return;
        }
//      Kiểm tra mã danh mục có tồn tại trong mảng các danh mục
//      Nếu mã danh mục có tồn tại, kiểm tra danh mục đó có chứa sản phẩm không
//      Nếu không chứa sản phẩm, tiến hành xóa trong mảng danh mục
        if (products[0] != null){
            for (int i = 0; i < products.length; i++) {
                if (products[i].getCatalogId()==categories[deleteCategoriesIdIndex].getCatalogId()){
                    System.out.println("Danh mục này có tồn tại sản phẩm nền không thể xóa!");
                    return;
                }
            }
        }
        //duyệt từ vị trí phần tử cần xóa
        for (int i = deleteCategoriesIdIndex; i < categories.length-1; i++) {
            if (categories[i] == null){
                break;
            }
            categories[i] = categories[i+1];
        }
        System.out.println("Xóa danh mục thành công");
    }

    //5. Cập nhật trạng thái danh mục
    public void updateStatus(Scanner scanner) {
// cho phép người dùng nhập mã danh mục cần cập nhật trạng thái,
        if (checkListNull()){
            return;
        }
        System.out.println("Nhập mã danh mục cần sửa");
        int updateCategoriesStatus = Integer.parseInt(scanner.nextLine());
        int updateCategoriesStatusIndex = findIndexCategoryById(updateCategoriesStatus);
// nếu tồn tại mã danh mục,
        if (updateCategoriesStatusIndex == -1){ //không thấy danh mục
            System.out.println("Không tìm thấy danh mục yêu cầu");
            return;
        }
//        thấy danh mục
// tiến hành cập nhật trạng thái danh mục
        System.out.println("Nhập trạng thái muốn cập nhật");
        categories[updateCategoriesStatusIndex].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
        System.out.println("Trạng thái mới của danh mục là : "+(categories[updateCategoriesStatusIndex].isCatalogStatus()? "Hoạt động" : "Không hoạt động"));
    }

    public int findIndexCategoryById(int id) {
        for (int i = 0; i <  categories.length; i++) {
            if (categories[i] != null && categories[i].getCatalogId() ==id){
                return i;
            }
        }
        return -1;
    }


    public boolean checkListNull(){
        if (categories[0]==null){
            System.out.println("Hiện chưa có danh mục nào");
            return true;
        }
        return false;
    }
}
