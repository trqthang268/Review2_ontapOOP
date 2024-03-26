package ra.entity;

import java.util.Scanner;

public class Categories {
    public static int currentId = 0;
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
        this.catalogId = ++currentId;
//        this.catalogStatus = true;
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(Scanner scanner, Categories[] arrCategories){
//        Nhập tên danh mục, có độ dài tối đa 50 ký tự, không trùng lặp
        while (true){
            System.out.println("Nhập tên danh mục");
            this.catalogName = scanner.nextLine();
//            kiểm tra bỏ trống hay không và check trùng lặp tên
            if (catalogName.trim().isEmpty() && existCatelogName(arrCategories) && catalogName.length()>50){
                System.err.println("Vui lòng nhập lại tên");
            }else{
                break;
            }
        }
//        nhập mô tả danh mục
        System.out.println("Nhập mô tả danh mục");
        while (true){
            this.descriptions = scanner.nextLine();
//            kiểm tra có bỏ trống hay không
            if (this.descriptions.trim().isEmpty()){
                System.err.println("Vui lòng nhập lại mô tả");
            }else{
                break;
            }
        }
//        System.out.println("Nhập trạng thái danh mục (true – hoạt động, false – không hoạt động)");
//        this.catalogStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData(){
        System.out.printf("| Mã danh mục : %-2d | Tên danh mục : %-15s | \n| Mô tả danh mục : %-40s\n| Trạng thái danh mục : %-10s\n",this.catalogId,this.catalogName,this.descriptions,(this.catalogStatus?"Hoạt động":"Không hoạt động"));
    }

    public boolean existCatelogName(Categories[] categories){
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].getCatalogName().equals(this.catalogName)){
                return true;
            }
        }
        return false;
    }
}
