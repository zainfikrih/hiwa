package men.ngopi.zain.hiwa;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.translucentbar.library.TranslucentBarManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import men.ngopi.zain.hiwa.adapter.MessageAdapter;
import men.ngopi.zain.hiwa.viewmodel.MessageViewModel;
import men.ngopi.zain.hiwa.model.Message;

public class HistoryFragment extends Fragment {

    private View view;
    static private HistoryFragment historyFragment;
    private List<Message> messages = new ArrayList<>();

    @BindView(R.id.rv_history)
    RecyclerView recyclerView;

    public static HistoryFragment newInstance(){
        historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this,view);

        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent( this, view, R.color.colorAccent);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        MessageAdapter messageAdapter= new MessageAdapter(getActivity(), getActivity().getSupportFragmentManager(), messages);
        recyclerView.setAdapter(messageAdapter);

        MessageViewModel viewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        viewModel.getMessages().observe(HistoryFragment.this, messageAdapter::setMessage);

        return view;
    }
}
