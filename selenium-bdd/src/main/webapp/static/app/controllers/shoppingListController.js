app.controller("ShoppingListController", function ($scope, ShoppingListService) {
    $scope.input = {
        value: null
    };

    $scope.shoppingList = {
        rows: []
    };

    function reload() {
        ShoppingListService.getShoppingList(function (items) {
            $scope.shoppingList.rows = [];
            for (var i = 0; i < items.length; i++) {
                $scope.shoppingList.rows.push({
                    uuid: items[i].uuid,
                    title: items[i].title,
                    date: new Date(items[i].timestamp)
                });
            }
        });
    }

    $scope.addItem = function () {
        if ($scope === null) {
            return;
        }
        var title = $scope.input.value.trim();
        if (title.length === 0) {
            return;
        }
        ShoppingListService.addItem(title, reload);
    };

    $scope.deleteItem = function (uuid) {
        ShoppingListService.deleteItem(uuid, reload);
    };

    reload();
});