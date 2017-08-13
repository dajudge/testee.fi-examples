app.service("ShoppingListService", function (ServerService) {
    this.getShoppingList = function (success) {
        ServerService.get("/shoppingList", function (response) {
            success(response.data.items);
        }, function (response) {
            // TODO do something useful here
            console.log("Error loading shopping list", response);
        });
    };

    this.deleteItem = function (uuid, success) {
        ServerService.delete("/shoppingList/" + uuid, function () {
            success();
        }, function (response) {
            // TODO do something useful here
            console.log("Error deleting item", response);
        });
    }

    this.addItem = function (title, success) {
        var payload = {title: title};
        ServerService.post("/shoppingList", payload, function () {
            success();
        }, function (response) {
            // TODO do something useful here
            console.log("Error adding new item", response);
        });
    };
});