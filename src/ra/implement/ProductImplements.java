package ra.implement;

import ra.entity.Product;

import java.sql.Struct;
import java.util.Scanner;

import static ra.implement.CategoriesImplements.categories;

public class ProductImplements {
    public static Product[] products = new Product[100];
    Scanner scanner = new Scanner(System.in);
    public void addProductItem(Scanner scanner){
        System.out.println("Nhập sô lượng sản phẩm muốn thêm mới");
        int productCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < productCount; i++) {
            Product newProduct = new Product();
            System.out.println("Nhập thông tin của sản phẩm thứ " + (i + 1));
            newProduct.inputData(scanner, products,categories,true);
            for (int j = 0; j < products.length; j++) {
                if (products[j]==null){
                    products[j] = newProduct;
                    break;
                }
            }
            System.out.println("Thêm sản phẩm thành công");
        }
    }

    public void displayAllProduct(){
        if (checkProductList()){
            return;
        }
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null){
                break;
            }
            products[i].displayData();
        }

    }

    private boolean checkProductList() {
        if (products[0] == null){
            System.out.println("Danh sach sản phẩm rỗng");
            return true;
        }
        return false;
    }

    public void updateProductById(Scanner scanner){
        if (checkProductList()){
            return;
        }
        System.out.println("Nhập mã sản phẩm cần cập nhật");
        String updateProductId = scanner.nextLine();
        int updateProductIdIndex = findIndexById(updateProductId);
        if (updateProductIdIndex != -1){
            System.out.println("Thông tin cũ");
            products[updateProductIdIndex].displayData();
            System.out.println("Mời nhập thông tin mới cho danh mục này");
            products[updateProductIdIndex].inputData(scanner, products, categories, false);
            System.out.println("Cập nhật thành công, thông tin mới như sau:");
            products[updateProductIdIndex].displayData();
        }else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    public void deleteProductById(Scanner scanner){
        if (checkProductList()){
            return;
        }
        System.out.println("Nhập mã sản phẩm cần cập nhật");
        String deleteProductId = scanner.nextLine();
        int deleteProductIdIndex = findIndexById(deleteProductId);
        if (deleteProductIdIndex != -1){
            for (int i = deleteProductIdIndex; i < products.length; i++) {
                if (products[i] == null){
                    break;
                }
                products[i] = products[i+1];
            }
            System.out.println("Xóa thành công");
        }
    }

    public void sortByPrice(Scanner scanner)
    {
        System.out.println("Nhập 1 : sắp xếp tăng dần, 2 : sắp xếp giảm dần");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                for (int i = 0; i < products.length; i++) {
                    if (products[i] == null){
                        break;
                    }
                    for (int j = 0; j < products.length - i - 1; j++) {
                        if (products[j + 1] == null){
                            break;
                        }
                        if (products[j].getPrice() > products[j + 1].getPrice()) {
                            Product temp = products[j];
                            products[j] = products[j + 1];
                            products[j + 1] = temp;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < products.length; i++) {
                    if (products[i] == null){
                        break;
                    }
                    for (int j = 0; j < products.length - i - 1; j++) {
                        if (products[j + 1] == null){
                            break;
                        }
                        if (products[j].getPrice() < products[j + 1].getPrice()) {
                            Product temp = products[j];
                            products[j] = products[j + 1];
                            products[j + 1] = temp;
                        }
                    }
                }
                break;
            default:
                System.out.println("Nhập lựa chọn sai");
                break;
        }
    }

    public void searchByName(Scanner scanner){
        if (checkProductList()){
            return;
        }
        System.out.println("Mời nhập tên sản phẩm cần tìm");
        String searchNameProduct = scanner.nextLine().toLowerCase();
        boolean checkSearch = false;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null){
                return;
            }
            if (products[i].getProductName().contains(searchNameProduct)){
                System.out.println("Sản phẩm có tên trùng khớp với tên đã nhập là :");
                System.out.println(products[i].getProductName());
                checkSearch = true;
            }
        }
        if (!checkSearch){
            System.out.println("Không tìm thấy sản phẩm giống với tên đã nhập");
        }
    }

    public void searchProductInRange(Scanner scanner){
        if (checkProductList()){
            return;
        }
        System.out.println("Nhập giá trị của giới hạn giá phía dưới");
        float lowerPriceLimit = Float.parseFloat(scanner.nextLine());
        float upperPriceLimit;
        while (true)
        {
            System.out.println("Nhập giá trị của giới hạn giá phía trên");
            upperPriceLimit = Float.parseFloat(scanner.nextLine());
            if (upperPriceLimit <= lowerPriceLimit)
                System.out.println("Giới hạn giá phía trên phải lớn hơn giới hạn giá phía dưới");
            else break;
        }
        System.out.println("Danh sách các sản phẩm trong khoảng giá:");
        boolean checkFindProduct = false;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null){
                return;
            }
            if (products[i].getPrice() >= lowerPriceLimit && products[i].getPrice() <= upperPriceLimit) {
                System.out.println(products[i].getProductName());
                checkFindProduct = true;
            }
        }
        if (!checkFindProduct){
            System.out.println("Không có sản phẩm nào trong khoảng giá đó");
        }
    }
    public int findIndexById(String idProduct){
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId().equals(idProduct)){
            return i;
            }
        }
        return -1;
    }
}
