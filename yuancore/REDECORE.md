# 1.ui/dialog
  方式1：
   ColorDialog dialog = new ColorDialog(this);
           dialog.setTitle(getString(R.string.operation));
           dialog.setContentText(getString(R.string.content_text));
           dialog.setContentImage(getResources().getDrawable(R.mipmap.sample_img));
           dialog.setPositiveListener(getString(R.string.delete), new ColorDialog.OnPositiveListener() {
               @Override
               public void onClick(ColorDialog dialog) {
                   Toast.makeText(MainActivity.this, dialog.getPositiveText().toString(), Toast.LENGTH_SHORT).show();
               }
           })
           .setNegativeListener(getString(R.string.cancel), new ColorDialog.OnNegativeListener() {
               @Override
               public void onClick(ColorDialog dialog) {
                   Toast.makeText(MainActivity.this, dialog.getNegativeText().toString(), Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
               }
           }).show();
           
   方式2：
     new PromptDialog(this).setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                 .setTitleText("Success").setContentText("Your info text goes here. Loremipsum dolor sit amet, consecteturn adipisicing elit, sed do eiusmod.")
                 .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                     @Override
                     public void onClick(PromptDialog dialog) {
                         dialog.dismiss();
                     }
                 }).show();
                        
     还需要进一步封装实现;
                        
                        
           