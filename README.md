*Note: If you would like to take a look and express your opinion about the code, I will be very happy and grateful. Anything that can help me learn is well-received. Thank you!*
### Spring application with the purpouse of learning Hibernate and REST.

This is a simple application like a MarketPlace. It will have Products within Categories. The products belong to specific Brands found in specific Cities. It will also have Users and a Cart. The possibility to buy items, manage them and show them.
In the initial commit I already did the Product - Category part.

Althought this is an application to increase my Spring knowledge, it will also help me create a close relation to Git. I will use different branches for different features to really simulate a team developing process. 

The next feature that I want to add will be the Branding part. The products will belong to Brands and the Brands will be found in different cities. After that I will start with the User feature.

For the interface I think React will be my choice, but I am not convinced yet. 

### 14/07/2019
Two days ago I updated the market-feature branch. Now it contains Brands and Cities. I managed to create every request possible so I can get used to it and then I will keep the important ones.

I will go with the authentification system next. I'll make a different branch that does not include the market feature, so everything will be decoupled. I'll use Spring Security.

### 17/07/2019  
I have made a list of usefull endpoints. This was done on cart-feature branch, but I'll merge them together soon.
##ENDPOINTS

#### USER:
  * /profile                               GET         -get current profile(user)
  * /profile/edit                          GET/ PUT    -get current profile/ update current logged in user
  * /register                              POST        -register a new user
  
#### CART:
  * /cart                                  GET         -get current cart
  * /cart/add/{product_id}                 PUT         -add product to cart
  
#### CATEGORY:                 
  * /categories                            GET         -get all categories
  * /categories/{name}                     GET         -get category by name
  * /categories-by-names                   GET         -get categories by names
  
#### ADMIN:
  * /manage/users/all                      GET         -get all users
  * /manage/roles/all                      GET         -get all roles
  * /manage/roles/add                      POST        -add new role
  * /manage/categories/add                 POST        -add new category
  * /manage/categories/update              PUT         -update category
  * /manage/categories/drop/{name}         DELETE      -drop category by name
  * /manage/carts                          GET         -get all carts
  * /manage/products/delete/all
  
#### PRODUCT:
  * /products                              GET         -get all products
  * /products/{product_id}                 GET         -get product by id
  * /products                              POST        -create new product
  * /products                              PUT         -update existing product
  * /products/drop/{product_id}            DELETE      -drop product by id
  * /products/rm-w/{product_id}            PUT         -remove warranty from product
  * /products/add-w/{product_id}           PUT         -add warranty to product
  * /products/category/{name}              GET         -get products by category name
  * /products/seller/{name}                GET         -get products by seller name
     
