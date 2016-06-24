package service;

import po.FavoriteItem;

public interface FavoriteService {
    /**
     * 往数据库增加一条favoriteItem信息
     */
    void favar(FavoriteItem favoriteItem);

    /**
     * 删除数据库的一条favoriteItem信息
     */
    void unfavor(FavoriteItem favoriteItem);
}
