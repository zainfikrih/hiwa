package men.ngopi.zain.hiwa.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.ArrayList;
import java.util.List;

import men.ngopi.zain.hiwa.HistoryFragment;
import men.ngopi.zain.hiwa.HomeFragment;
import men.ngopi.zain.hiwa.MainActivity;
import men.ngopi.zain.hiwa.R;
import men.ngopi.zain.hiwa.database.AppDatabase;
import men.ngopi.zain.hiwa.database.MessageDao;
import men.ngopi.zain.hiwa.model.Message;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private FragmentManager fragmentManager;
    private Context context;
    private List<Message> message;
    private LayoutInflater layoutInflater;
    private View view;

    public MessageAdapter (Context context, FragmentManager fragmentManager, List<Message> message){
        this.context = context;
        this.message = message;
        this.fragmentManager = fragmentManager;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = layoutInflater.inflate(R.layout.message_item, viewGroup, false);
        return new MessageAdapter.MessageViewHolder(view);
    }

    public void setMessage(List<Message> message){
        this.message = message;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageViewHolder menuViewHolder, final int i) {
        menuViewHolder.phone.setText(message.get(i).getmPhone());
        menuViewHolder.message.setText(message.get(i).getmMessage());

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeFragment homeFragment = HomeFragment.newInstance();
//                Bundle bundle = new Bundle();
//                bundle.putString("phone", message.get(i).getmPhone());
//                bundle.putString("message", message.get(i).getmMessage());
//                homeFragment.setArguments(bundle);
//
//                fragmentManager.beginTransaction().replace(R.id.frame_content, homeFragment).commit();
//                MainActivity.getInstance().selectedNavigation(R.id.action_item1);
//            }
//        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Message msg = new Message();
                msg.setmId(message.get(i).getmId());
                msg.setmPhone(message.get(i).getmPhone());
                msg.setmMessage(message.get(i).getmMessage());

                // Create Alert using Builder
                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context)
                        .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                        .setTitle("Are you sure?")
                        .setMessage("This history will be permanently deleted and cannot be restored.")
                        .addButton("DELETE", -1, context.getResources().getColor(R.color.colorRed), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, (dialog, which) -> {
                            try {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MessageDao messageDao = AppDatabase.getInstance(MainActivity.getInstance().getApplicationContext()).messageDao();

                                        messageDao.delete(msg);
                                    }
                                }).start();
                            } catch (Exception e){

                            } finally {
                                message.clear();
                            }
                            dialog.dismiss();
                        });

                // Show the alert
                builder.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView phone;
        private TextView message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            phone = itemView.findViewById(R.id.item_phone);
            message = itemView.findViewById(R.id.item_message);
        }
    }
}

