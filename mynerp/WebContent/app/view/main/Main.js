/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('Mynerp.view.main.Main', {
    //extend: 'Ext.container.Container',
    //plugins: 'viewport'
    extend: 'Ext.container.Viewport', // posso usar as outras duas linhas de cima no lugar dessa
    xtype: 'app-main',
    
    requires: [
        'Mynerp.view.main.MainController',
        'Mynerp.view.main.MainModel',

        'Mynerp.view.main.Header',
        'Mynerp.view.main.Footer',
        'Mynerp.view.main.Panel',
        'Mynerp.view.menu.Accordion'
    ],
    
    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'border'
    },

    items: [

        {
            xtype: 'mainpanel',
            region: 'center'  
        },
        {
            xtype: 'appheader',
            region: 'north'  
        },
        {
            xtype: 'appfooter',
            region: 'south'  
        },
        {
            xtype: 'mainmenu',
            region: 'west'
        }]

});
