Ext.define('Mynerp.view.staticData.condicaoPagamento.condicaoPagamentoController', {
    extend: 'Mynerp.view.staticData.base.staticDataController',

	requires: [
        'Mynerp.view.staticData.base.staticDataController'
    ],
    
     alias: 'controller.condicoesPagamento',

    onAddCondicaoParcelaClick: function(button, e, options){        
        var me = this,
            isEdit = me.isEdit,
            dialog = me.dialog,
            grid = me.lookupReference('gridParcelasCondicao'),
            store = grid.getStore(),
            seq = store.count() + 1;

            if (!isEdit) {
                store.setSession(null);
            } 
            

            record = new Mynerp.model.staticData.financeiro.CondicaoParcela({
                sequencial: seq
            });            

        //grid.getStore().insert(0, linha); este "insert" me permite escolher a posição pelo index
        store.add(record); // este insere sempre no final
        grid.getPlugin('cellplugin').startEditByPosition({
             row: 0,
             column: 0
         });
    },

    onRemoveCondicaoParcelaClick: function(grid, rowIndex){
        var me = this;
        console.log('passou');
        grid.getStore().getAt(rowIndex).drop();
    },

    onSave: function(button, e, options){

        var me = this,
            dialog = me.dialog,
            view = me.getView(),
            isEdit = me.isEdit,
            session = me.getSession(),
            grid = button.up('grid'),
            gridParcelas = me.lookupReference('gridParcelasCondicao'),
            store = grid.getStore(),
            form = me.lookupReference('condicaoPagamentoForm'),
            id;


        if (form.isValid()) {
			dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe
        	//validações adiconais
        	//TODO
        	//

        	//dialog.getSession().save(); //salvou as alterações da tela na sessão e no servidor, a store ainda não sabe

        	if(isEdit) {
        		var batch = session.getSaveBatch();
            
	            if (batch){
	                //var batchCallback = batch.start(); // start dispara as atualizações e retorna um batch tbem
	                //batchCallback.on("complete", function ( batch, operation, eOpts) {
	                batch.start(); // start dispara as atualizações e retorna um batch tbem
	                batch.on("complete", function ( batch, operation, eOpts) {
	                   try {
	                        if (!me.idObjeto) {
	                            me.idObjeto = batch.operations[0].getResultSet().getRecords()[0].id; 
	                        }
	                        codigoCondicao = me.idObjeto;
	                        Mynerp.util.Util.showToast('Condição de Pagamento Nº '+ codigoCondicao + ' alterada com sucesso!'); 
	                   } catch (e) {
	                        Mynerp.util.Util.showToast('Registro atualizado!');
	                   }      
	                });
	                //associatedStore.sync();
	            }
        	} else {
        		id = dialog.getViewModel().get('currentCadastro').id;
                store.add(session.getRecord(me.tipoObjeto, id)); // aqui adiciona o registro salvo da store pra não precisar buscar denovo com o sync

                store.sync({
	                success: function(form, action) {
	                    me.onCancel(); //fecha a janelinha
	                    me.refresh(button); //chama outro método logo abaixo
	                    Mynerp.util.Util.showToast(form.proxy.getReader().rawData.mensagemRetorno);
	                },
	                failure: function(form, action) {
	                    Mynerp.util.Util.handleFormFailure(action);
	                }
	            });
        	}  	         

        }

    },

});

