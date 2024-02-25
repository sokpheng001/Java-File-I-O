package online.hackpi.view;

public class Thumbnail {
    public static void choice(){
        System.out.println("==============================================================================");
        System.out.println("                         Welcome to product Store                         ");
        System.out.println("==============================================================================");
        System.out.print(("""
                1. Create new 2. List all products (from store) 3. Update 4. Delete 5. Back Up\s
                6. Restore 7. Random Record 8. Commit (from transaction to store) \s
                9. Search Product 10. Exit 11. Destroy (Don't use it) 0. Set Row
                """).toUpperCase());
        System.out.println("==============================================================================");
//        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
    }
}
