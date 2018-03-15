Ext.define('Mynerp.view.financeiro.contaPagar.quitacoes.QuitarParcelasController', {
    extend: 'Mynerp.view.base.ViewController',

    alias: 'controller.quitarparcelascp',

    onBuscarParcelas: function(button, e, options) {
        var me = this,
        store = me.getStore('parcelasquitarcp'),
        grid = me.lookupReference('parcelaQuitarGridCp');

        if (grid.getSelectionModel().getCount() > 0) {

            Ext.MessageBox.confirm('Confirmação', 'Ao recarregar a lista as alterações feitas serão descartadas. <br> Deseja Prosseguir?', 
                function (button, text) {
                    if (button == 'yes') {
                        grid.getSelectionModel().deselectAll();
                        store.load();
                }  
            });
        } else {
            store.load();
        }        
    },

    onDeselect: function(sel, record, index, eOpts) {
    	var me = this;
    	record.set('dataQuitacao', null);
    	me.atualizaTotais(sel);
    },

    onSelect: function(sel, record, index, eOpts) {
    	var me = this;
    	record.set('dataQuitacao', new Date());
    	me.atualizaTotais(sel)
    },

    onItemClick: function(view, record, item, index, e, eOpts ) {
        //this.redirectTo('contasreceber/' + record.get('id'));
        var me = this;
            me.recordSelecionado = record;
    },

    onBlurData: function (field, event, eOpts) {

        var me = this,          
            grid = me.lookupReference('parcelaQuitarGridCp'),
            record = me.recordSelecionado;

        if(field.getValue() != null) {
            if (Ext.isDate(field.getValue())) {
                grid.getSelectionModel().select (record, true);
            } else {
                Mynerp.util.Util.showErrorMsg("Data inválida: " + field.getValue());
            }           
        } else {
            grid.getSelectionModel().deselect (record);
        }

    },  

    atualizaTotais: function(sel) {
    	var me = this,    		
    		grid = me.lookupReference('parcelaQuitarGridCp'),
    		registros = me.lookupReference('contaRegistros'),
    		total = me.lookupReference('total');
    		store = grid.getStore();

    		valor = 0;
    		store.each(function (record) {
    			if (record.get('dataQuitacao') != null) {
    				valor = valor + record.get('valor');
    			}
    		});
    		valor = Ext.util.Format.currency(valor, 'R$ ', 2)

    		registros.setValue(sel.getCount());
    		total.setValue(valor);
    },

    onQuitar: function (button, e, options) {
        var me = this,          
            grid = me.lookupReference('parcelaQuitarGridCp'),
            store = grid.getStore();

            if (grid.getSelectionModel().getCount() <= 0) {
                Mynerp.util.Util.showAlertMsg('Nenhuma parcela selecionada para quitação.')
            } else {
                store.sync({
                success: function(batch, options) {
                    var response = batch.proxy.getReader().rawData.mensagemRetorno;
                        store.load();
                        Mynerp.util.Util.showToast(response);
                        grid.getSelectionModel().deselectAll();
                },
                failure: function(batch, options) {
                    var response = batch.proxy.getReader().rawData.mensagemRetorno;

                    if (response && response != null) {
                        Mynerp.util.Util.showErrorMsg(response);
                    } else {
                        Mynerp.util.Util.handleStoreSyncFailure(batch, options);
                    }     
                }
                });
            }
            
    },

    beforeChangePage: function ( toolbar, page, eOpts ) {
        var me = this,          
            grid = me.lookupReference('parcelaQuitarGridCp');
            grid.getSelectionModel().deselectAll();
    }

   });