
import React, { useEffect, useRef } from 'react';
import { Shield, Baby, UserCheck, Heart, Calendar, ArrowRight } from 'lucide-react';
import { Button } from "@/components/ui/button";

const vaccines = [
  {
    id: 1,
    category: 'Trẻ em',
    title: 'Vaccine cho trẻ em',
    description: 'Các vaccine thiết yếu cho trẻ sơ sinh và trẻ nhỏ giúp bảo vệ khỏi các bệnh phổ biến ở trẻ em.',
    icon: Baby,
    color: 'bg-blue-50',
    iconColor: 'text-blue-500',
  },
  {
    id: 2,
    category: 'Người lớn',
    title: 'Vaccine cho người lớn',
    description: 'Vaccine được khuyến nghị cho người lớn để duy trì miễn dịch và bảo vệ khỏi các mối đe dọa mới.',
    icon: UserCheck,
    color: 'bg-green-50',
    iconColor: 'text-green-500',
  },
  {
    id: 3,
    category: 'Người cao tuổi',
    title: 'Chăm sóc người cao tuổi',
    description: 'Vaccine chuyên biệt cho người cao tuổi để tăng cường miễn dịch và ngăn ngừa biến chứng nghiêm trọng.',
    icon: Heart,
    color: 'bg-purple-50',
    iconColor: 'text-purple-500',
  },
  {
    id: 4,
    category: 'Du lịch',
    title: 'Vaccine cho người đi du lịch',
    description: 'Vaccine bảo vệ dành cho người đi du lịch để phòng ngừa các bệnh phổ biến ở các điểm đến cụ thể.',
    icon: Calendar,
    color: 'bg-orange-50',
    iconColor: 'text-orange-500',
  },
];

const VaccineInfo = () => {
  const sectionRef = useRef<HTMLDivElement>(null);
  const cardsRef = useRef<(HTMLDivElement | null)[]>([]);

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add('opacity-100');
            entry.target.classList.remove('opacity-0');
          }
        });
      },
      { threshold: 0.1 }
    );

    const currentSectionRef = sectionRef.current;
    if (currentSectionRef) {
      observer.observe(currentSectionRef);
    }

    cardsRef.current.forEach((card) => {
      if (card) {
        observer.observe(card);
      }
    });

    return () => {
      if (currentSectionRef) {
        observer.unobserve(currentSectionRef);
      }
      cardsRef.current.forEach((card) => {
        if (card) {
          observer.unobserve(card);
        }
      });
    };
  }, []);

  return (
    <section id="vaccines" className="py-20 bg-white">
      <div 
        ref={sectionRef} 
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <Shield className="h-4 w-4 mr-1" />
            <span>Dịch vụ của chúng tôi</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            Giải pháp <span className="text-primary">Vaccine</span> Toàn diện
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Chúng tôi cung cấp đa dạng các loại vaccine cho mọi lứa tuổi. Đội ngũ chuyên gia của chúng tôi 
            đảm bảo tiêm chủng an toàn và hiệu quả để bảo vệ bạn và gia đình.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {vaccines.map((vaccine, index) => (
            <div
              key={vaccine.id}
              ref={(el) => (cardsRef.current[index] = el)}
              className={`vaccine-card glass rounded-xl p-6 opacity-0 transition-all duration-1000 delay-${index * 100}`}
              style={{ transitionDelay: `${index * 100}ms` }}
            >
              <div className={`${vaccine.color} rounded-full w-12 h-12 flex items-center justify-center mb-4`}>
                <vaccine.icon className={`h-6 w-6 ${vaccine.iconColor}`} />
              </div>
              <div className="text-sm font-semibold text-primary mb-2">
                {vaccine.category}
              </div>
              <h3 className="text-xl font-medium mb-3">{vaccine.title}</h3>
              <p className="text-gray-600 mb-6">{vaccine.description}</p>
              <Button variant="ghost" className="text-primary hover:text-primary/90 hover:bg-primary/10 px-0">
                Tìm hiểu thêm
                <ArrowRight className="ml-1 h-4 w-4" />
              </Button>
            </div>
          ))}
        </div>

        <div className="mt-16 text-center">
          <Button className="bg-primary hover:bg-primary/90 text-white btn-hover-effect">
            Xem tất cả dịch vụ
          </Button>
        </div>
      </div>
    </section>
  );
};

export default VaccineInfo;
