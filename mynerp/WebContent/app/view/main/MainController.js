/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('Mynerp.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    requires: [
        //'Ext.window.MessageBox'
        'Mynerp.util.Util'
    ],

    alias: 'controller.main',

    onClickButton: function () {
        Ext.Msg.confirm('Confirmação', 'Você tem certeza?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    },

    onLogout: function(button,e,options) {

        var me = this;
        Ext.Ajax.request({
            url: 'userAuthenticationServlet?action=logout', 
            scope: me,
            success: 'onLogoutSuccess',
            failure: 'onLogoutFailure'
        });

    },
    
    onLogoutFailure: function(conn, response, options, eOpts) {
        
        Mynerp.util.Util.showErrorMsg(conn.responseText);
    },

    onLogoutSuccess: function(conn, response, options, eOpts) {
        
        var result = Mynerp.util.Util.decodeJSON(conn.responseText);
        
        if (result.success) {
            this.getView().destroy();
            window.location.reload();
        } else {
            Mynerp.util.Util.showErrorMsg(result.msg);
        }
    }


});
