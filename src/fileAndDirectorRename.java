import java.io.File;

public class fileAndDirectorRename {
    public static void main(String args[]) {
        String path = "F:\\新建文件夹";
        File pathFile = new File(path);
        if (pathFile.isDirectory()) {//判断是不是文件夹,第一级文件夹不更名
            File[] files = pathFile.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    reName(file, path);
                }
            }
        }
    }

    public static void reName(File file, String path) {
        if (file.isDirectory()) {//判断二级文件是一个文件夹时，先更名，再深入到三级文件
            String oldName = file.getName();
            System.out.println(oldName);
            int startIndex = oldName.indexOf("（");
            String newPath = path + "\\" + oldName.substring(0,startIndex-1);
            file.renameTo(new File(newPath));
            System.out.println("2" + file.getName());
            File[] newFiles = file.listFiles();
            String oldPath = file.getAbsolutePath();
            for(File f : newFiles) {
                reName(f, oldPath);
            }


        }else if (file.isFile()) {//判断这个文件不是文件夹,只是一个文件
            String oldName = file.getName();
            int startIndex = oldName.indexOf("（");
            int endIndex = oldName.lastIndexOf(".");
            String newPath = path + "\\" + oldName.substring(0, startIndex - 1) + oldName.substring(endIndex);
            file.renameTo(new File(newPath));

        }

    }
}
