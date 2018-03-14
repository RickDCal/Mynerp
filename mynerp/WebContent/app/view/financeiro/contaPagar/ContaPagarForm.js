Ext.define('Mynerp.view.financeiro.contaPagar.ContaPagarForm', {
	extend: 'Ext.form.Panel',
	xtype: 'conta-pagar-form',

	//reference: 'clienteForm', // já foi passado na window, referente a este form que é item dela

	requires: [
   		'Mynerp.util.Util',
   		'Mynerp.util.Glyphs',
   		'Mynerp.model.configuracao.ParametroFinanceiro'
   		//'Mynerp.model.staticData.financeiro.Cobranca'
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
				xtype: 'fieldset',
				title: 'Dados da conta',

				items: [
					   {
					   xtype: 'container',
					   layout: 'hbox',
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
						   labelPad: 2,
						   padding: '0 10 10 0'
					   },
					   items: [{
						   name: 'id',
						   fieldLabel: 'Código',
						   width: 110,
						   editable: false,
						   //flex: 1,
						   bind : '{currentCadastro.id}'
						   //allowBlank: false
						   },
						   {
						   xtype: 'datefield',
						   fieldLabel: 'Data',
						   flex: 1.5,
						   //width: 180,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,						   
						   bind : '{currentCadastro.dataEmissaoConta}'
						   },
						   {
						   fieldLabel: 'Código',
						   reference: 'codFornecedor',
						   xtype: 'getidfield',
			               xtypeLista: 'lista-fornecedor-window',
			               modelName: Mynerp.model.cadastro.Fornecedor,
			               nameFieldReference: 'nomeDoFornecedor',						   
						   maskRe: /[0-9]/i,
						   maxLength: 6,
						   flex: 1,
						   allowBlank: false,
						   afterLabelTextTpl: Mynerp.util.Util.required,
						   bind : '{currentCadastro.idPessoa}'
						   //bind : '{currentCadastro.pessoa.id}'
						   },
						   {
						   fieldLabel: 'Fornecedor',
						   reference: 'nomeDoFornecedor',
						   maxLength: 60,
						   flex: 6,
						   editable: false,
						   bind : '{currentCadastro.fornecedor.nome}'
					   	   }
						   
					   ]},
						{
						   xtype: 'container',
						   layout: 'hbox',
						   
						   defaults: {
							   flex: 1,
							   xtype: 'textfield',
							   maxLength: 100,
						   	   enforceMaxLength: true,
						   	   maxLengthText: 'Máximo de {0} caracteres.',
							   msgTarget: 'side',
							   labelWidth: 105,
							   labelAlign: 'top',
							   labelPad: 2,
							   padding: '0 10 10 0'
						   },
						   items: [
							   {
						   	   fieldLabel: 'Nº NF/Doc.',
							   maxLength: 50,
							   flex: 1,
							   bind : '{currentCadastro.numeroDocumento}'
							   },
							   {
							   xtype: 'numberfield',
							   hideTrigger: true,
							   fieldLabel: 'Valor Total',
							   flex: 1,
							   allowBlank: false,
							   afterLabelTextTpl: Mynerp.util.Util.required,						   
							   bind : '{currentCadastro.valorTotal}'
							   },
							   {
							   fieldLabel: 'Observação',
							   maxLength: 200,
							   flex: 6.5,
							   bind :'{currentCadastro.observacao}'
							   }
						   ]
						},
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
							    // {
							    //     xtype: 'grid-edit-parcela',
							    //     flex: 1
						    	// }

						    	//////------------------------------------------------------------------//////

						    	{
						    		xtype: 'grid',
						    		reference: 'gridParcelasCP',
						    		bind: '{currentCadastro.parcelas}',
						    		border: true,
						    		height: 300,
								    columnLines: true,
								    viewConfig: {
								        stripeRows: true
								    },

								    selModel: {selType: 'cellmodel'} ,
								    plugins: [Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit: 1, pluginId: 'cellplugin'})],

								    columns: [		        

										        {
										            text: '#',
										            width: 20,
										            sortable: false,
										            dataIndex: 'sequencial'
										            //allowBlank: false, ver se tem que colocar isso no editor.....
										        },
										        {
											        text: 'Emissão',
											        width: 80,
											        dataIndex: 'dataEmissao',
											        xtype: 'datecolumn',
											        width: null,
											        format: 'd/m/Y',
											        editor: {
											            xtype: 'datefield',
											            format: 'Y-m-d',
											            minValue: '01/01/15',
											            allowBlank: false
											            //disabledDays: [0, 6],
											            //disabledDaysText: 'Plants are not available on the weekends'
											        }
											    },
										        {
										            text: 'Nº Docto.',
										            width: 100,
										            dataIndex: 'numeroDocto',
										            editor: {
										            	xtype: 'textfield',
										            	maxLength: 50,
										            	allowBlank: false
										            }		            
										        }, 
										        {
											        text: 'Vencimento',
											        dataIndex: 'dataVencimento',
											        xtype: 'datecolumn',
											        width: null,
											        format: 'd/m/Y',
											        editor: {
											            xtype: 'datefield',
											            format: 'Y-m-d H:i:s',
											            minValue: '01/01/15'
											            //disabledDays: [0, 6],
											            //disabledDaysText: 'Plants are not available on the weekends'
											        }
											    },
											    {
											        text: 'Quitacão',
											        dataIndex: 'dataQuitacao',
											        xtype: 'datecolumn',
											        width: null,
											        format: 'd/m/Y',
											        editor: {
											            xtype: 'datefield',
											            format: 'Y-m-d H:i:s',
											            minValue: '01/01/15'
											            //disabledDays: [0, 6],
											            //disabledDaysText: 'Plants are not available on the weekends'
											        }
											    },
											    {
											        header: 'Valor',
											        dataIndex: 'valor',
											        width: 70,
											        align: 'right',
											        formatter: 'usMoney',
											        editor: {
											            xtype: 'numberfield',
											            allowBlank: false,
											            minValue: 0,
											            maxValue: 100000
											        }
											    },		        
										        // { // esta coluna não é necessária ... manter somente para testes
										        //     text: 'cod Cobranca',
										        //     flex: 1,
										        //     dataIndex: 'idCobranca',
										        //     reference: 'idDaCobranca',
										        //     //hidden: true
										        // },

										        {
											        text: 'Cobranca',
											        reference: 'nomeCobranca',
											        dataIndex: 'cobrancaGridParcela', //não precisa ser uma propriedade existente no record
											        flex: 1,
											        renderer: function(value, metadata, record) {
											        	if (!record.dirty) {
											        		value = record.get('nomeCobranca');
											        	}
											        	return value;
											        },

											        editor: {
											        	xtype: 'getidnamefield',
											        	chamador: 4,
									               		xtypeLista: 'lista-cobranca-window',
									               		codigoDataIndex: 'idCobranca', 
    													nomeDataIndex: 'cobrancaGridParcela', 
									               		idFieldReference: 'idDaCobranca',												   
												   		allowBlank: false,
												   		editable: false										            
											        }
											    },
										        {
										            header: 'Observação',
										            width: 100,
										            dataIndex: 'observacao',
										            editor: {
										            	xtype: 'textfield',
										            	maxLength: 100,
														enforceMaxLength: true,
														maxLengthText: 'Máximo de {0} caracteres.'
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
											            handler: 'onRemoveParcelaClick'
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
								                        text: 'Add',
								                        glyph: Mynerp.util.Glyphs.getGlyph('add'),
								                        handler: 'onAddParcelaClick' 
								                    },
								                    {                                        
								                        xtype: 'tbseparator'
								                    },
								                    {
								                        xtype: 'button',
								                        itemId: 'gerarParcelas',
								                        text: 'Gerar Parcelas',
								                        glyph: Mynerp.util.Glyphs.getGlyph('listaNum'),
								                        handler: 'onGerarParcelaClick'

								                    }
								                ]
								            }
								    ]

						    	}
						    	/////------------------------------------------------------------------///////
					        ]
						}
					]
				}				
			]
});