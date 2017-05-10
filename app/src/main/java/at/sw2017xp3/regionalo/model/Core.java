package at.sw2017xp3.regionalo.model;

/**
 * Created by jo on 26.04.17.
 */

public class Core {

    ProductManager products_;
    UserManager Users_;

    private static Core  instance_;
    private Core() {
        products_ = new ProductManager();
        Users_ = new UserManager();
    }

    public ProductManager getProducts()
    {
        return  products_;
    }


    public UserManager getUsers()
    {
        return  Users_;
    }

    public static Core getInstance()
    {
        if (instance_ == null)
        {
            instance_ = new Core();
        }
        return instance_;
    }
}






