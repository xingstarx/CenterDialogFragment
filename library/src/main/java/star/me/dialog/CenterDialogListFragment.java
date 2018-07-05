package star.me.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.star.dialog.R;
import star.me.dialog.utils.ScreenUtils;
import star.me.dialog.utils.ToastUtils;

public class CenterDialogListFragment extends DialogFragment {
    public static final String TAG = "CenterDialogListFragment";
    RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private int[] resIdList = new int[]{R.drawable.ic_share_black, R.drawable.ic_share_black, R.drawable.ic_share_black};
    private String[] titleList = new String[]{"分享", "移动", "删除"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new Adapter();
        mAdapter.setData(generateData());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        ToastUtils.show(view.getContext(), "分享");
                        dismiss();
                        break;
                    case 1:
                        ToastUtils.show(view.getContext(), "移动");
                        dismiss();
                        break;
                    case 2:
                        ToastUtils.show(view.getContext(), "删除");
                        dismiss();
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()
                .setLayout(ScreenUtils.getScreenWidth(getContext()) * 4 / 5,
                        WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().setCanceledOnTouchOutside(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.customDialogFragment);
    }

    private List<CommandItem> generateData() {
        List<CommandItem> itemList = new ArrayList<>();
        for (int index = 0; index < resIdList.length; index++) {
            CommandItem item = new CommandItem();
            item.resId = resIdList[index];
            item.title = titleList[index];
            itemList.add(item);
        }
        return itemList;
    }

    class CommandItem {
        public int resId;
        public String title;
    }

    class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<CommandItem> itemList = new ArrayList<>();
        OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public void setData(List<CommandItem> dataList) {
            itemList = dataList;
        }

        @NonNull
        @Override

        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dialog_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            CommandItem item = itemList.get(position);
            viewHolder.iconView.setImageResource(item.resId);
            viewHolder.titleView.setText(item.title);
            if (onItemClickListener != null) {
                viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView titleView;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.icon);
            titleView = itemView.findViewById(R.id.title);
            rootView = itemView.findViewById(R.id.root_view);
        }
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public static CenterDialogListFragment newInstance() {
        CenterDialogListFragment fragment = new CenterDialogListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
