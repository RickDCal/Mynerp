Ext.define('Mynerp.view.configuracao.ConfiguracoesController', {
	extend: 'Mynerp.view.base.ViewController',

	alias: 'controller.configuracoes',

	requires: [
		'Mynerp.util.Util',
		'Mynerp.util.Glyphs'
	],

	onTreeNodeClick: function(view, record, item, index, event, options)  {

		var me = this,
            configMain = me.lookupReference('panelConfiguracoes'),
            className = record.get('className'),
            rec, store;
        
        var mesmoPanel = configMain.items.findBy(function(panel) {
        	return panel.title === record.get('text');
        });  

        switch (className) {
            case 'parametro-coleta-form' : 
                store = me.getStore('parametroColeta');
                rec = store.getAt(0);
            break;
            
            default:
            break;
        }

        if (!mesmoPanel) {
			if (configMain.items.length > 0) {
				configMain.removeAll();
			}       	
        	activePanel = configMain.add({
        		xtype: className,
        		viewModel: {
                links: {
                    currentCadastro: rec
                }
            },
            session: true //child session

        	});
        }           

		// var mainPanel = this.getMainPanel();
		// var newTab = mainPanel.items.findBy(
		// 	function (tab) {
		// 		return tab.title === record.get('text');
		// 	});

		// if (!newTab) {
		// 	newTab = mainPanel.add({
		// 		xtype: record.get('className'),
		// 		closable: true,
		// 		glyph: record.get('glyph') + '@FontAwesome',
		// 		title: record.get('text')
		// 	});

		// }






		// var menuPanel= Ext.ComponentQuery.query('config-menu'),
		// 	store = Ext.data.StoreManager.lookup('configuracoes-store');
		// 	//store = menuPanel.getStore('menuconfiguracoes');
		// 	console.log(view);
		// 	console.log(menuPanel);
		// 	console.log(store);
		// 	view.bindStore(store);

			

		// console.log(menuPanel);
		// console.log(store);
	}
})