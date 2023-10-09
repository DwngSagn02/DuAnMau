package com.example.duanmau_sangldph42693;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_sangldph42693.fragment.DoanhThuFragment;
import com.example.duanmau_sangldph42693.fragment.DoiMkFragment;
import com.example.duanmau_sangldph42693.fragment.QLLoaiSachFragment;
import com.example.duanmau_sangldph42693.fragment.QLPhieuMuonFragment;
import com.example.duanmau_sangldph42693.fragment.QLSachFragment;
import com.example.duanmau_sangldph42693.fragment.QLThanhVienFragment;
import com.example.duanmau_sangldph42693.fragment.ThemNguoiDungFragment;
import com.example.duanmau_sangldph42693.fragment.Top10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    SharedPreferences sharedPreferences;
    private String level,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView  = findViewById(R.id.navigationView);

        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        level = sharedPreferences.getString("level", "");
        name = sharedPreferences.getString("name","");

        View header =navigationView.getHeaderView(0);
        TextView userName = header.findViewById(R.id.userFullname);

        userName.setText("Xin chào "+name+" ("+level+")");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        QLPhieuMuonFragment qlPhieuMuonFragment = new QLPhieuMuonFragment();
        replaceFrg(qlPhieuMuonFragment);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setTitle("Thư viện Phương Nam");
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.mQLPhieuMuon) {
                    getSupportActionBar().setTitle("Quán lý phiếu mượn");
                    QLPhieuMuonFragment qlPhieuMuonFragment = new QLPhieuMuonFragment();
                    replaceFrg(qlPhieuMuonFragment);

                } else if (item.getItemId() == R.id.mQLLoaiSach) {
                    getSupportActionBar().setTitle("Quản lý loại sách");
                    QLLoaiSachFragment qlLoaiSachFragment = new QLLoaiSachFragment();
                    replaceFrg(qlLoaiSachFragment);
                } else if (item.getItemId() == R.id.mQLSach) {
                    getSupportActionBar().setTitle("Quản lý sách");
                    QLSachFragment qlSachFragment = new QLSachFragment();
                    replaceFrg(qlSachFragment);
                } else if (item.getItemId() == R.id.mQLThanhVien) {
                    getSupportActionBar().setTitle("Quản lý thành viên");
                    QLThanhVienFragment qlThanhVienFragment = new QLThanhVienFragment();
                    replaceFrg(qlThanhVienFragment);
                } else if (item.getItemId() == R.id.mTop10) {
                    getSupportActionBar().setTitle("Top 10 sách mượn nhiều nhất");
                    Top10Fragment top10Fragment = new Top10Fragment();
                    replaceFrg(top10Fragment);
                } else if (item.getItemId() == R.id.mDoanhThu) {
                    getSupportActionBar().setTitle("Doanh thu");
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                } else if (item.getItemId() == R.id.mThemNguoiDung) {
                    if (level.equalsIgnoreCase("admin")){
                        getSupportActionBar().setTitle("Thêm người dùng");
                        ThemNguoiDungFragment themNguoiDungFragment = new ThemNguoiDungFragment();
                        replaceFrg(themNguoiDungFragment);
                    }else {
                        Toast.makeText(MainActivity.this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
                    }

                } else if (item.getItemId() == R.id.mDoiMatKhau) {
                    getSupportActionBar().setTitle("Đổi mật khẩu");
                    DoiMkFragment doiMkFragment = new DoiMkFragment();
                    replaceFrg(doiMkFragment);
                } else if (item.getItemId() == R.id.mThoat) {
                    exit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public void exit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        builder.setNegativeButton("Không",null);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, DangNhap.class));
            }
        });
        builder.show();
    }
}
