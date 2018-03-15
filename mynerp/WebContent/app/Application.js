/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */

Ext.require('Mynerp.view.login.Login');
Ext.require('Mynerp.view.main.Main');
Ext.require('Mynerp.view.menu.Tree');



Ext.define('Mynerp.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Mynerp',

    glyphFontFamily: 'FontAwesome',

    requires: [
        'Mynerp.overrides.tree.ColumnOverride',
        'Mynerp.overrides.grid.column.Action',
        'Mynerp.overrides.json.encodeFormatoData',
        'Mynerp.overrides.patch.data.ModelWithId' //ExtJS 5 bug fix - remove this once Sencha fixes it
    ],

    views: [
        'login.Login' // já espera que esteja dentro do diretório de views da aplicação
    ],

    controllers: [
        //'Root',
        'Menu'
        //'StaticData'
        //,'Test'////pula atela de login para testes
    ],

    stores: [
        // TODO: add global / shared stores here
    ],
    
    launch: function () {
    	
    	Ext.tip.QuickTipManager.init();
    	
    	var me = this;
        
        var task = new Ext.util.DelayedTask(function() {
        	me.splashscreen.fadeOut({
        		duration:1000,
        		remove: true
        	});
        	
        	me.splashscreen.next().fadeOut({
        		duration:1000,
        		remove: true, 
        		listeners: {
        			afteranimate: function(el, startTime, eOpts) {
        				
        				//Ext.create('Mynerp.view.login.Login');//também poderia ter chamado a janela desta forma
                        Ext.widget('login-dialog');
        				//console.log('launch');
        			}
        		}
        	});        	
        	
        	//Ext.getBody().unmask();
        });
        
        task.delay(2000);      
        
    },

    init: function () {
    	var me = this;
//
//        // Start the mask on the body and get a reference to the mask
        me.splashscreen = Ext.getBody().mask('Carregando Aplicação', 'splashscreen');
//
//        // Add a new class to this mask as we want it to look different from the default.
        me.splashscreen.addCls('splashscreen');
//
//        // Insert a new div before the loading icon where we can place our logo.
        Ext.DomHelper.insertFirst(Ext.query('.x-mask-msg')[0], {
            cls: 'x-splash-icon'
        });
    }
});
