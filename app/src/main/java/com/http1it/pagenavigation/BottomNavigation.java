package com.http1it.pagenavigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.http1it.pagenavigation.fragment.FragmentMenu;
import com.http1it.pagenavigation.fragment.FragmentOrder;
import com.http1it.pagenavigation.fragment.FragmentUser;

public class BottomNavigation extends LinearLayout {
    private LinearLayout llBootmUser, llBootmMenu, llBootmOrder;
    private ImageView llBootmUserImage, llBootmMenuImage, llBootmOrderImage;
    private TextView llBootmUserText, llBootmMenuText, llBootmOrderText;

    private static int cursor;
    private Context context;

    private Fragment currentFragment;
    private Fragment fragmentUser;
    private Fragment fragmentMenu;
    private Fragment fragmentOrder;

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_bottom_navigation, this);
        this.context = context;
        cursor = 0;
        initView();
        initClick();
    }

    /**
     * 点击菜单时触发
     */
    public void OnClickMenu() {
        Log.d("底部导航栏", "ClickMenu");
        addFrameLayout(fragmentMenu);
    }

    /**
     * 点击订单时触发
     */
    public void OnClickOrder() {
        Log.d("底部导航栏", "ClickOrder");
        addFrameLayout(fragmentOrder);
    }

    /**
     * 点击用户时触发
     */
    public void OnClickUser() {
        Log.d("底部导航栏", "ClickUser");
        addFrameLayout(fragmentUser);
    }

    /**
     * 将碎片加载到碎片布局FrameLayout
     */
    public void addFrameLayout(Fragment fragment) {
        //FragmentTransaction需要再次创建
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment).show(fragment);
            } else {
                fragmentTransaction.show(fragment);
            }
        } else {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment).add(R.id.flFragment, fragment);
            } else {
                fragmentTransaction.add(R.id.flFragment, fragment);
            }
        }
        currentFragment = fragment;
        //动画
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private void initView() {
        //初始化碎片处理
        fragmentUser = new FragmentUser();
        fragmentMenu = new FragmentMenu();
        fragmentOrder = new FragmentOrder();
        currentFragment = null;
        //初始化默认菜单为首页
        addFrameLayout(fragmentMenu);

        llBootmUser = findViewById(R.id.llBootmUser);
        llBootmMenu = findViewById(R.id.llBootmMenu);
        llBootmOrder = findViewById(R.id.llBootmOrder);

        llBootmUserImage = findViewById(R.id.llBootmUserImage);
        llBootmMenuImage = findViewById(R.id.llBootmMenuImage);
        llBootmOrderImage = findViewById(R.id.llBootmOrderImage);

        llBootmUserText = findViewById(R.id.llBootmUserText);
        llBootmMenuText = findViewById(R.id.llBootmMenuText);
        llBootmOrderText = findViewById(R.id.llBootmOrderText);
    }

    private void initClick() {
        //点击菜单时
        llBootmMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor == 0)
                    return;
                OnClickMenu();
                llBootmMenuText.setTextColor(getResources().getColor(R.color.clickBlue));
                llBootmMenuImage.setImageResource(R.mipmap.ico_tab_menu_2);
                llBootmMenu.setBackgroundResource(R.color.white);

                //其他没有点中的背景颜色、字体、图片设置为默认的
                llBootmUserText.setTextColor(getResources().getColor(R.color.gray));
                llBootmOrderText.setTextColor(getResources().getColor(R.color.gray));
                llBootmUserImage.setImageResource(R.mipmap.ico_tab_user_1);
                llBootmOrderImage.setImageResource(R.mipmap.ico_tab_order_1);
                llBootmOrder.setBackgroundResource(R.color.bgGray);
                llBootmUser.setBackgroundResource(R.color.bgGray);
                cursor = 0;
            }
        });
        //点击订单时
        llBootmOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor == 1)
                    return;
                OnClickOrder();
                llBootmOrderText.setTextColor(getResources().getColor(R.color.clickBlue));
                llBootmOrderImage.setImageResource(R.mipmap.ico_tab_order_2);
                llBootmOrder.setBackgroundResource(R.color.white);

                //其他没有点中的背景颜色、字体、图片设置为默认的
                llBootmUserText.setTextColor(getResources().getColor(R.color.gray));
                llBootmMenuText.setTextColor(getResources().getColor(R.color.gray));
                llBootmUserImage.setImageResource(R.mipmap.ico_tab_user_1);
                llBootmMenuImage.setImageResource(R.mipmap.ico_tab_menu_1);
                llBootmMenu.setBackgroundResource(R.color.bgGray);
                llBootmUser.setBackgroundResource(R.color.bgGray);
                cursor = 1;
            }
        });
        //点击用户时
        llBootmUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor == 2)
                    return;
                OnClickUser();
                llBootmUserText.setTextColor(getResources().getColor(R.color.clickBlue));
                llBootmUserImage.setImageResource(R.mipmap.ico_tab_user_2);
                llBootmUser.setBackgroundResource(R.color.white);

                //其他没有点中的背景颜色、字体、图片设置为默认的
                llBootmMenuText.setTextColor(getResources().getColor(R.color.gray));
                llBootmOrderText.setTextColor(getResources().getColor(R.color.gray));
                llBootmMenuImage.setImageResource(R.mipmap.ico_tab_menu_1);
                llBootmOrderImage.setImageResource(R.mipmap.ico_tab_order_1);
                llBootmMenu.setBackgroundResource(R.color.bgGray);
                llBootmOrder.setBackgroundResource(R.color.bgGray);
                cursor = 2;
            }
        });
    }
}
