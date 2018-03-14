Ext.define('Mynerp.view.base.ViewController', {
	extend: 'Ext.app.ViewController',

	requires: [
		'Mynerp.util.Util',
		'Mynerp.util.Glyphs'
	],

	onAdd: function (button, e, options) {
		this.createDialog(null);
	},
	
	/*onEdit: function (button) {
		this.createDialog(button.getWidgetRecord());
	},
	*/

	onEdit: function (button) { // override no onEdit da base.ViewController
        var me = this;
        try {
            me.createDialog(button.getWidgetRecord()); // se o botão estiver na linha do grid
        } catch (e) {// se o botão estiver na barra
        	record = me.getRecordSelected(button)[0];
            if (!!record) {
                me.createDialog(record);
            } else {
                Mynerp.util.Util.showAlertMsg('Nenhum registro selecionado para edição.');
            } 
        }                
    },

	onCancel: function (button, e, options) {
		var me = this; 
		me.dialog = Ext.destroy(me.dialog);
	},

	onDelete: function (button, e, options) {  // atenção em algumas classes este método é sobreposto por outros de mesmo nome
		var record = button.getWidgetRecord();

		Ext.Msg.show({
			title: 'Delete',
			msg: 'Tem certeza que deseja excluir?',
			buttons: Ext.Msg.YESNO,
			icon: Ext.Msg.QUESTION,
			
			fn: function (buttonId) {
				if (buttonId == 'yes') {
					//record.drop();
                    record.erase();
				}
			}
		});
	},

	onGetIdTriggerClick: function(campoCodigo, e, options) {
		var me = this,
			xtypeLista = campoCodigo.xtypeLista,
			chamador = campoCodigo.chamador,
			campoNome = me.lookupReference(campoCodigo.nameFieldReference);
        	me.createListaSelecaoDialog(campoCodigo, campoNome, xtypeLista, chamador);

	},

	onGetIdNameTriggerClick: function(campoNome, e, options) {
		var me = this,
			xtypeLista = campoNome.xtypeLista,
			chamador = campoNome.chamador,
			campoCodigo = me.lookupReference(campoNome.idFieldReference);
        	me.createListaSelecaoDialog(campoCodigo, campoNome, xtypeLista, chamador);
	},

	onGetIdNameClearClick: function(campoNome, e, options){
		var me = this,
			campoCodigo = me.lookupReference(campoNome.idFieldReference);

		if (campoCodigo && !campoCodigo.isColumn) {
            campoCodigo.setValue(null);
            campoNome.setValue(null);
        } else {
            grid2 = campoNome.up('grid'); //grid2 é o grid onde temos que colocar os valores
            rec = grid2.getSelection()[0];
            atributo1 = campoNome.codigoDataIndex;
            atributo2 = campoNome.nomeDataIndex; //este atributo não precisa ser do record pois só vai ser mostrado no grid. mas vai ser incorporado ao record e por isso o dataindex vai ser reconhecido no grid como sendo dado do record e vai exibir
            rec.set(atributo1,null);
            rec.set(atributo2,null);
            campoNome.setValue(null);
            campoNome.fireEvent('change');
        }
        campoNome.getTrigger('limpar').hide();
        campoNome.updateLayout();

    },

	createListaSelecaoDialog: function(campoCodigo, campoNome, xtypeLista, chamador){
        var me = this,
            view = me.getView(),
            glyphs = Mynerp.util.Glyphs;

        	me.listaDialog = view.add({
            	xtype: xtypeLista,
            	campoCodigo: campoCodigo,
            	campoNome: campoNome,
            	chamador: chamador,

            	viewModel: {
                	data: {
                    	//title: 'Localizar', // cada lista tem sua propriedade de título
                    	glyph: glyphs.getGlyph('lupa')
                	}
            	}
        	});

        me.listaDialog.show();

    },    

	onGetIdFieldChange: function (field) {
		var me = this,
			campoNome = me.lookupReference(field.nameFieldReference),
			modelCarregar = field.modelName,
			id;

			try {
				id = parseInt(field.getValue());
			} catch (e) {
				id = field.getValue();
			}
			

			if (field.getValue() != '' && field.getValue() != null) {
				
				modelCarregar.load(id,{
					scope: this,
					failure: function(record, operation) {
						campoNome.setValue(null);
						Mynerp.util.Util.showToast('Registro não encontrado: '+id);
						field.setValue(null);
					},
					success: function(record, operation) {
					},
					callback: function(record, operation, success) {
					    if (success) {
							campoNome.setValue(record.get('nome'));	
						}
					}
				});

			} else {
				campoNome.setValue(null);
			}          	         
	
	},

	onGetIdFieldEnter: function (field, e, eOpts) {
		var me = this;

		if (e.getKey() == e.ENTER || e.getKey() == e.TAB) {
			me.onGetIdFieldChange(field);
		}
	},

	onGetIdFieldBlur: function (field, e, eOpts) {
		var me = this;
		//me.onGetIdFieldChange(field);		
	},

	onTeste: function() {
		console.log('teste');
	},

	viewSessionChanges: function () { //util para testes
        var changes = this.getView().getSession().getChanges();
        if (changes !== null) {
            new Ext.window.Window({
                autoShow: true,
                title: 'Session Changes',
                modal: true,
                width: 600,
                height: 400,
                layout: 'fit',
                items: {
                    xtype: 'textarea',
                    value: JSON.stringify(changes, null, 4)
                }
            });
        } else {
            Ext.Msg.alert('No Changes', 'There are no changes to the session.');
        }
    },


    onDeleteFromToolbar: function(button, e, options) {
    	var me = this,  
    	 	grid = button.up('grid'),          
            store = grid.getStore(),            
         	record = me.getRecordSelected(button)[0]; // se o botão estiver na barra de ferramentas
        	
     	if (!!record) {
     		me.delete(record, store);
     	} else {
     		Mynerp.util.Util.showAlertMsg('Nenhum registro selecionado para exclusão.');
     		
     	}        	
    },

    delete: function(record, store) {
    	Ext.Msg.show({
            title: 'Delete',
            msg: 'Tem certeza que deseja excluir?',
            buttons: Ext.Msg.YESNO,
            icon: Ext.Msg.QUESTION,
            fn: function (buttonId) {
                if (buttonId == 'yes') {
                    store.remove(record);
                    store.sync({
                        success: function(batch, options) {    
                            Mynerp.util.Util.showToast(batch.proxy.getReader().rawData.mensagemRetorno);
                        },
                        failure: function(batch, options) {
                            store.getModel().load(codigo);
                            store.load();
                        }
                    });
                }
            }
        });
    },


    showToastRetorno: { //Este é um objeto que possui as funções de callback dentro
                        success: function(batch, options) {    
                            Mynerp.util.Util.showToast(batch.proxy.getReader().rawData.mensagemRetorno);
                        },
                        failure: function(batch, options) {
                            // no model base já tem um listener de exception que retorna a mensagem que vem do servidor.
                        }
                      }
    

});