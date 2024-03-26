package ra.run;

import ra.implement.ProductImplements;

import java.util.Scanner;

public class ProductManagement {
    static ProductImplements productImplements = new ProductImplements();
    public void displayProductMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát");
            System.out.println("Nhập lựa chọn");
            switch (Byte.parseByte(scanner.nextLine())) {
                case 1:
                    productImplements.addProductItem(scanner);
                    break;
                case 2:
                    productImplements.displayAllProduct();
                    break;
                case 3:
                    productImplements.sortByPrice(scanner);
                    break;
                case 4:
                    productImplements.updateProductById(scanner);
                    break;
                case 5:
                    productImplements.deleteProductById(scanner);
                    break;
                case 6:
                    productImplements.searchByName(scanner);
                    break;
                case 7:
                    productImplements.searchProductInRange(scanner);
                    break;
                case 8:
                    return;
            }
        }
    }
}