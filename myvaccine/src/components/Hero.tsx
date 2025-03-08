
import React, { useEffect, useRef } from 'react';
import { Shield, ArrowRight } from 'lucide-react';
import { Button } from "@/components/ui/button";

const Hero = () => {
  const heroRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          entry.target.classList.add('opacity-100');
          entry.target.classList.remove('opacity-0');
        }
      },
      { threshold: 0.1 }
    );

    const currentRef = heroRef.current;
    if (currentRef) {
      observer.observe(currentRef);
    }

    return () => {
      if (currentRef) {
        observer.unobserve(currentRef);
      }
    };
  }, []);

  return (
    <div className="min-h-screen pt-20 flex items-center background-gradient overflow-hidden">
      <div 
        ref={heroRef} 
        className="section-container grid grid-cols-1 lg:grid-cols-2 gap-12 lg:gap-24 opacity-0 transition-opacity duration-1000"
      >
        <div className="flex flex-col justify-center">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6 w-fit animate-fade-in">
            <Shield className="h-4 w-4 mr-1" />
            <span>Bảo vệ sức khỏe gia đình bạn</span>
          </div>
          
          <h1 className="text-balance font-semibold leading-tight mb-6 animate-fade-in animate-delay-100">
            Hành trình sức khỏe của bạn <br />
            <span className="text-primary">Bắt đầu từ phòng ngừa</span>
          </h1>
          
          <p className="text-lg text-gray-600 mb-8 max-w-xl animate-fade-in animate-delay-200">
            Chúng tôi cung cấp dịch vụ tiêm chủng an toàn, hiệu quả được thực hiện bởi đội ngũ chuyên gia y tế. 
            Hãy chủ động bảo vệ bản thân và người thân của bạn khỏi các bệnh có thể phòng ngừa được.
          </p>
          
          <div className="flex flex-col sm:flex-row gap-4 animate-fade-in animate-delay-300">
            <Button className="bg-primary hover:bg-primary/90 text-white px-8 py-6 text-lg btn-hover-effect">
              Đặt lịch hẹn
              <ArrowRight className="ml-2 h-5 w-5" />
            </Button>
            <Button variant="outline" className="px-8 py-6 text-lg">
              Tìm hiểu thêm
            </Button>
          </div>
          
          <div className="flex items-center mt-12 animate-fade-in animate-delay-400">
            <div className="flex -space-x-2">
              {[1, 2, 3].map((i) => (
                <div key={i} className="inline-block h-10 w-10 rounded-full ring-2 ring-white bg-blue-100 flex items-center justify-center">
                  <span className="text-primary font-medium text-sm">{i}k+</span>
                </div>
              ))}
            </div>
            <p className="ml-4 text-sm text-gray-600">
              <span className="font-medium">Hơn 10.000</span> khách hàng tin tưởng chúng tôi
            </p>
          </div>
        </div>
        
        <div className="relative flex items-center justify-center">
          <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-72 h-72 bg-primary/10 rounded-full filter blur-3xl animate-pulse-slow"></div>
          <div className="relative z-10 glass rounded-2xl p-6 shadow-xl animate-float">
            <div className="w-full h-[400px] bg-blue-50 rounded-lg flex items-center justify-center">
              <div className="text-center p-8">
                <Shield className="h-24 w-24 text-primary mx-auto mb-6" />
                <h3 className="text-2xl font-medium mb-4">Tiêm chủng toàn diện</h3>
                <p className="text-gray-600">
                  Trung tâm của chúng tôi cung cấp đa dạng các loại vaccine cho mọi lứa tuổi,
                  đảm bảo bảo vệ khỏi nhiều bệnh có thể phòng ngừa được.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
