Ext.define('Mynerp.view.staticData.condicaoPagamento.CondicaoPagamentoForm', {
	extend: 'Ext.form.Panel',
	xtype: 'condicao-pagamento-form',

	//reference: 'clienteForm', // já foi passado na window, referente a este form que é item dela

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs'
   ],

			bodyPadding: 10,
			//width: 800, // o layout da janela é fit então não precisa destas medidas qui no filho, pois ajanela já tem medida
			//height: 500,
			layout: {
			type: 'anchor'
			},

			modelValidation: true, 
			scrollable: true,

			defaults: { //será aplicado no próximo array de itens que ele encontrar
				xtype: 'fieldset',
				layout: 'anchor',
				defaults: {
				anchor: '100%'
				}
			},        

			items: [
				//identificação
				{
				title: 'Cadastro',
				height: 390,

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
					   defaultFocus: 'id', // acho que não funcionou
					   combineErrors: true,
					   defaults: {
						   flex: 1,
						   xtype: 'textfield',
						   maxLength: 100,
						   enforceMaxLength: true,
						   maxLengthText: 'Máximo de {0} caracteres.',
						   msgTarget: 'side',
						   labelWidth: 105,
						   labelAlign: 'top',
						   padding: '0 10 10 0'
					   },
					   items: [{
						   name: 'id',
						   fieldLabel: 'Código',
						   width: 110,
						   editable: false,
						   flex: 1,
						   bind : '{currentCadastro.id}'
						   //allowBlank: false
						   },
						   {
						   fieldLabel: 'Nome',
						   maxLength: 60,
						   flex: 4,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   bind : '{currentCadastro.nome}'
						   }	   
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   combineErrors: true,
						   defaults: {
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   padding: '0 10 10 0'
						   },
						   items: [
							   {
								xtype: 'checkboxfield',
								//fieldLabel: ' ',
								hideEmptyLabel: false,
								flex: 2,
								boxLabel: 'Conta Receber',
		                    	name: 'indCR',
		                    	//checked: true,
		                    	inputValue: 'pagar',
		                    	uncheckedValue: false,                    		
	                    		bind: '{currentCadastro.isReceber}'
							   },
							   {
								xtype: 'checkboxfield',
								//fieldLabel: ' ',
								hideEmptyLabel: false,
								flex: 2,
								boxLabel: 'Conta Pagar',
		                    	name: 'indCP',
		                    	//checked: true,
		                    	inputValue: 'receber',
		                    	uncheckedValue: false,                    		
	                    		bind: '{currentCadastro.isPagar}'
							   }
							      
					   ]},
					   {
							xtype: 'fieldcontainer',
					        fieldLabel: 'Parcelas',
					        labelAlign: 'top',
					        combineErrors: true,
					        msgTarget : 'top',
					        layout: 'hbox',
					        defaults: {
					            flex: 1,
					            hideLabel: true
					        },
					        items: [
						    	{
						    		xtype: 'grid',
						    		reference: 'gridParcelasCondicao',
						    		bind: '{currentCadastro.parcelas}',
						    		border: true,
						    		//height: 100,
								    columnLines: true,
								    viewConfig: {
								        stripeRows: true
								    },

								    selModel: {selType: 'cellmodel'} ,
								    plugins: [Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit: 1, pluginId: 'cellplugin'})],

								    columns: [		        

										        {
										            text: 'Nº',
										            width: 20,
										            sortable: false,
										            dataIndex: 'sequencial'
										            //allowBlank: false, ver se tem que colocar isso no editor.....
										        },
										        {
											        text: 'Dias Prazo',
											        width: 100,
											        dataIndex: 'prazo',
											        //xtype: 'textfiel',
											        //width: null,
											        //format: 'd/m/Y',
											        editor: {
											            xtype: 'numberfield',
											            hideTrigger: true,
											            allowBlank: false,
											            minValue: 0,
											            allowDecimals : false
											            //disabledDays: [0, 6],
											            //disabledDaysText: 'Plants are not available on the weekends'
											        }
											    },
										        {
											        text: 'Percentual',
											        //width: 100,
											        flex: 1,
											        dataIndex: 'percentual',
											        editor: {
											            xtype: 'numberfield',
											            hideTrigger: true,
											            allowBlank: false,
											            minValue: 0,
											            maxValue: 100,
											            allowDecimals : true
											        }
											    },
										        {
											        xtype: 'actioncolumn',
											        width: 30,
											        sortable: false,
											        menuDisabled: true,
											        items: [{
											            icon: 'resources/images/grid/delete.png',
											            tooltip: 'Excluir parcela',
											            handler: 'onRemoveCondicaoParcelaClick'
											        }]
										    	}
										    ],

								    dockedItems: [
								            {
								                xtype: 'toolbar',
								                dock: 'top',
								                itemId: 'topToolbar',
								                items: [
								                    {
								                        xtype: 'button',
								                        itemId: 'add',
								                        text: 'Inserir',
								                        glyph: Mynerp.util.Glyphs.getGlyph('add'),
								                        handler: 'onAddCondicaoParcelaClick' 
								                    }								                    
								                ]
								            }
								    ]

						    	}
						    	/////------------------------------------------------------------------///////
					        ]
						},
					   {
						   xtype: 'container',
						   layout: 'hbox',
						   hidden: false, 
						   //combineErrors: true, //mostra todos os erros juntos
						   defaults: {							   
							   msgTarget: 'side',
							   labelAlign: 'top',
							   padding: '0 10 10 0'
						   },
						   items: [
						   		{
							   	xtype: 'container',
							   	name: 'containerVazio',
								layout: 'anchor',
								hidden: false,
								flex: 2
							    },	
						   	   {
							   xtype: 'datefield',
							   fieldLabel: 'Data de Desativação',
							   flex: 1,
							   labelWidth: 200,
							   hidden: false,
							   bind : '{currentCadastro.dataExclusao}'
							   }
							   		   	   
						   ]
						}
					]
				}				
			]

});