
import React from 'react';
import { Shield, ChevronRight, Facebook, Twitter, Instagram, Youtube, Mail, Phone, MapPin } from 'lucide-react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

const Footer = () => {
  const currentYear = new Date().getFullYear();
  
  return (
    <footer id="contact" className="bg-gray-100 pt-16 pb-8">
      <div className="section-container">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8 mb-12">
          <div>
            <div className="flex items-center space-x-2 mb-6">
              <Shield className="h-8 w-8 text-primary" />
              <span className="text-xl font-semibold tracking-tight">Trung Tâm Tiêm Chủng</span>
            </div>
            <p className="text-gray-600 mb-6">
              Đối tác đáng tin cậy của bạn cho các dịch vụ tiêm chủng toàn diện. Chúng tôi cam kết 
              bảo vệ sức khỏe của bạn và sức khỏe của người thân.
            </p>
            <div className="flex space-x-4">
              <a href="#" className="bg-white rounded-full p-2 text-gray-600 hover:text-primary transition-colors">
                <Facebook className="h-5 w-5" />
              </a>
              <a href="#" className="bg-white rounded-full p-2 text-gray-600 hover:text-primary transition-colors">
                <Twitter className="h-5 w-5" />
              </a>
              <a href="#" className="bg-white rounded-full p-2 text-gray-600 hover:text-primary transition-colors">
                <Instagram className="h-5 w-5" />
              </a>
              <a href="#" className="bg-white rounded-full p-2 text-gray-600 hover:text-primary transition-colors">
                <Youtube className="h-5 w-5" />
              </a>
            </div>
          </div>

          <div>
            <h4 className="text-lg font-medium mb-6">Liên kết nhanh</h4>
            <ul className="space-y-3">
              {[
                { name: 'Trang chủ', href: '#' },
                { name: 'Giới thiệu', href: '#about' },
                { name: 'Dịch vụ', href: '#vaccines' },
                { name: 'Địa điểm', href: '#locations' },
                { name: 'Đặt lịch hẹn', href: '#' },
                { name: 'Câu hỏi thường gặp', href: '#' },
              ].map((link) => (
                <li key={link.name}>
                  <a 
                    href={link.href} 
                    className="inline-flex items-center text-gray-600 hover:text-primary transition-colors"
                  >
                    <ChevronRight className="h-4 w-4 mr-2" />
                    {link.name}
                  </a>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h4 className="text-lg font-medium mb-6">Liên hệ</h4>
            <ul className="space-y-4">
              <li className="flex items-start">
                <MapPin className="h-5 w-5 text-primary shrink-0 mt-0.5 mr-3" />
                <span className="text-gray-600">
                  123 Đại lộ Sức khỏe, Quận Trung tâm<br />Thành phố, 12345
                </span>
              </li>
              <li className="flex items-center">
                <Phone className="h-5 w-5 text-primary shrink-0 mr-3" />
                <a href="tel:+84281234567" className="text-gray-600 hover:text-primary transition-colors">
                  +84 (28) 123-4567
                </a>
              </li>
              <li className="flex items-center">
                <Mail className="h-5 w-5 text-primary shrink-0 mr-3" />
                <a href="mailto:info@trungtamtiemchung.com" className="text-gray-600 hover:text-primary transition-colors">
                  info@trungtamtiemchung.com
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h4 className="text-lg font-medium mb-6">Đăng ký</h4>
            <p className="text-gray-600 mb-4">
              Đăng ký nhận bản tin của chúng tôi để cập nhật thông tin về dịch vụ, khuyến mãi và lời khuyên về sức khỏe.
            </p>
            <div className="space-y-3">
              <div className="flex">
                <Input 
                  placeholder="Nhập email của bạn" 
                  className="rounded-r-none" 
                />
                <Button className="rounded-l-none bg-primary hover:bg-primary/90 text-white">
                  Đăng ký
                </Button>
              </div>
              <p className="text-xs text-gray-500">
                Bằng cách đăng ký, bạn đồng ý với Chính sách bảo mật và Điều khoản dịch vụ của chúng tôi.
              </p>
            </div>
          </div>
        </div>

        <div className="border-t border-gray-200 pt-6 text-center">
          <p className="text-gray-600">
            © {currentYear} Trung Tâm Tiêm Chủng. Bảo lưu mọi quyền.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
