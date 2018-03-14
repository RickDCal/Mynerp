/**
Código referente à tela de login
 */

Ext.define ('Mynerp.view.login.Login', {
	extend: 'Ext.window.Window',

	xtype: 'login-dialog',

	requires: [
		'Mynerp.view.login.LoginController' //, requer esta classe e já está indo chamar ela na linha de baixo pelo alias do controller
		//'Mynerp.view.locale.Translation'
	],

	controller: 'login', //note que na LoginController demos a ela um alias 'controller.login' que SEMPRE DEVE começar com controller e depois o alias
		
	autoShow: true,
	height: 170,
	width: 360,
	layout: {
		type: 'fit'
	},
	
	iconCls: 'fa fa-key fa-lg',
	title: 'Login',
	closeAction: 'hide',
	closable: false,
	draggable: false,
	resizable: false,

	items: [
		{
			xtype: 'form',
			reference: 'form', //poderia ter dado outro nome menos genérico
			bodyPadding: 15,
			defaults: {
				xtype: 'textfield',
				anchor: '100%',
				labelWidth: 60,
				allowBlank: false,
				//vtype: 'alphanum', 
				minLenght: '2',
				msgTarget: 'under',
				
				listeners: {
					specialKey: 'onTextFieldSpecialKey' //criei esta função lá na logincontroller pra chamar aqui no listener
				}				     
				
				//labelAlign: 'top'
			},
			items: [
				{					
					name: 'user',
					fieldLabel: 'Usuário'//,					
					//maxLength: 4//,
					//vtype: 'customUser'
				},
				{
					inputType: 'password',
					name: 'password', // quando o id do campo não é declarado o nome assume o papel de id
					id: 'password',
					fieldLabel: 'Senha',
					//maxLength: 8,
					//vtype: 'customPass',
					msgTarget: 'side',
					enableKeyEvents: true,
					listeners: {
						keypress: 'onTextFieldKeyPress'
					}
					
				}
			],
			
			dockedItems: [
				{
					xtype: 'toolbar',
					dock: 'bottom',
					items: [
						// {
						// 	xtype: 'translation'
						// },
						// {
						// 	xtype: 'tbfill'
						// },
						{
							xtype: 'button',
							iconCls: 'fa fa-times fa-lg',
							text: 'Cancelar',
							listeners: {
								click: 'onButtonClickCancel'
							}
						},
						{
							xtype: 'button',
							iconCls: 'fa fa-sign-in fa-lg',
							formBind: true,
							text: 'Enviar',
							listeners: {
								click: 'onButtonClickSubmit'
							}
						}
					]			   
				}
			]
		}
	]	
})