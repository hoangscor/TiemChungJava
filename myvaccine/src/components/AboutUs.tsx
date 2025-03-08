
import React, { useEffect, useRef } from 'react';
import { Shield, Award, Heart, Sparkles } from 'lucide-react';

const features = [
  {
    icon: Shield,
    title: 'An toàn là ưu tiên hàng đầu',
    description: 'Chúng tôi ưu tiên sự an toàn của bạn với quy trình nghiêm ngặt và vaccine chất lượng cao.'
  },
  {
    icon: Award,
    title: 'Chứng nhận chất lượng',
    description: 'Trung tâm của chúng tôi được chứng nhận và đội ngũ nhân viên được đào tạo bài bản.'
  },
  {
    icon: Heart,
    title: 'Chăm sóc tận tâm',
    description: 'Chúng tôi chăm sóc với sự đồng cảm, thấu hiểu và tôn trọng đối với từng bệnh nhân.'
  },
  {
    icon: Sparkles,
    title: 'Phương pháp hiện đại',
    description: 'Chúng tôi sử dụng các kỹ thuật và công nghệ mới nhất trong thực hành tiêm chủng.'
  }
];

const AboutUs = () => {
  const sectionRef = useRef<HTMLDivElement>(null);
  const contentRef = useRef<HTMLDivElement>(null);
  const statsRef = useRef<HTMLDivElement>(null);
  const featureRefs = useRef<(HTMLDivElement | null)[]>([]);

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

    const currentRefs = [
      sectionRef.current,
      contentRef.current,
      statsRef.current,
      ...featureRefs.current
    ].filter(Boolean);

    currentRefs.forEach(ref => {
      if (ref) {
        observer.observe(ref);
      }
    });

    return () => {
      currentRefs.forEach(ref => {
        if (ref) {
          observer.unobserve(ref);
        }
      });
    };
  }, []);

  return (
    <section id="about" className="py-20 bg-gradient-to-b from-blue-50 to-white">
      <div 
        ref={sectionRef}
        className="section-container opacity-0 transition-opacity duration-1000"
      >
        <div className="text-center mb-16">
          <div className="inline-flex items-center rounded-full px-3 py-1 text-sm font-medium bg-blue-50 text-primary mb-6">
            <Heart className="h-4 w-4 mr-1" />
            <span>Về chúng tôi</span>
          </div>
          <h2 className="text-balance font-medium mb-6">
            Đối tác <span className="text-primary">Y tế</span> đáng tin cậy của bạn
          </h2>
          <p className="text-gray-600 max-w-xl mx-auto">
            Chúng tôi cam kết bảo vệ sức khỏe và sự an lành của cộng đồng thông qua 
            các dịch vụ tiêm chủng an toàn, hiệu quả.
          </p>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center mb-16">
          <div 
            ref={contentRef}
            className="opacity-0 transition-opacity duration-1000 delay-200"
          >
            <h3 className="text-2xl font-medium mb-6">Sứ mệnh & Tầm nhìn</h3>
            <div className="space-y-6 text-gray-600">
              <p>
                Tại Trung Tâm Tiêm Chủng, chúng tôi tin tưởng vào sức mạnh của y học dự phòng. Sứ mệnh của chúng tôi là 
                cung cấp dịch vụ tiêm chủng chất lượng cao, dễ tiếp cận cho các cá nhân và gia đình ở mọi giai đoạn cuộc sống.
              </p>
              <p>
                Được thành lập vào năm 2010 bởi đội ngũ chuyên gia y tế, chúng tôi đã phát triển từ một phòng khám đơn lẻ thành 
                nhiều trung tâm, luôn duy trì cam kết về sự xuất sắc và chăm sóc lấy bệnh nhân làm trung tâm.
              </p>
              <p>
                Tầm nhìn của chúng tôi là tạo ra một cộng đồng khỏe mạnh hơn thông qua các dịch vụ tiêm chủng toàn diện, 
                giáo dục và các chương trình tiếp cận cộng đồng. Chúng tôi phấn đấu trở thành nhà cung cấp dịch vụ tiêm chủng 
                hàng đầu, được biết đến với chuyên môn, lòng trắc ẩn và sự đổi mới.
              </p>
            </div>
          </div>

          <div 
            ref={statsRef}
            className="grid grid-cols-2 gap-6 opacity-0 transition-opacity duration-1000 delay-300"
          >
            {[
              { number: '10+', label: 'Năm kinh nghiệm' },
              { number: '3', label: 'Địa điểm' },
              { number: '50+', label: 'Chuyên gia y tế' },
              { number: '100K+', label: 'Mũi tiêm đã thực hiện' }
            ].map((stat, index) => (
              <div key={index} className="glass rounded-xl p-6 text-center">
                <div className="text-3xl font-bold text-primary mb-2">{stat.number}</div>
                <div className="text-gray-600">{stat.label}</div>
              </div>
            ))}
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {features.map((feature, index) => (
            <div
              key={index}
              ref={(el) => (featureRefs.current[index] = el)}
              className="glass rounded-xl p-6 opacity-0 transition-all duration-1000"
              style={{ transitionDelay: `${400 + index * 100}ms` }}
            >
              <div className="bg-blue-50 rounded-full w-12 h-12 flex items-center justify-center mb-4">
                <feature.icon className="h-6 w-6 text-primary" />
              </div>
              <h3 className="text-xl font-medium mb-3">{feature.title}</h3>
              <p className="text-gray-600">{feature.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default AboutUs;
